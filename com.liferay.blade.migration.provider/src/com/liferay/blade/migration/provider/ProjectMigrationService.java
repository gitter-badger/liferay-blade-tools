package com.liferay.blade.migration.provider;

import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.api.ProgressMonitor;
import com.liferay.blade.java.api.Reporter;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.api.Migration;
import com.liferay.blade.migration.api.MigrationListener;
import com.liferay.blade.migration.api.ProjectMigrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;

@Component
public class ProjectMigrationService implements Migration {

	@Activate
	public void activate(BundleContext context) {
		_context = context;

		_migrationListenerTracker = new ServiceTracker<MigrationListener, MigrationListener>(context, MigrationListener.class, null);
		_migrationListenerTracker.open();

		_fileMigratorTracker = new ServiceTracker<FileMigrator, FileMigrator>(context, FileMigrator.class, null);
		_fileMigratorTracker.open();

		_projectMigratorTracker = new ServiceTracker<ProjectMigrator, ProjectMigrator>(context, ProjectMigrator.class, null);
		_projectMigratorTracker.open();
	}

	@Override
	public List<Problem> findProblems(final File projectDir, final ProgressMonitor monitor) {
		monitor.beginTask("Searching for migration problems in " + projectDir, -1);

		final List<Problem> problems = new ArrayList<>();

		ServiceReference<ProjectMigrator>[] projectMigrators = _projectMigratorTracker.getServiceReferences();

		if (projectMigrators != null && projectMigrators.length > 0) {
			for (ServiceReference<ProjectMigrator> projectMigratorRef : projectMigrators) {
				if (!monitor.isCanceled()) {
					try {
						ProjectMigrator projectMigrator = _context.getService(projectMigratorRef);
						List<Problem> migrationProblems = projectMigrator.analyze(projectDir);

						problems.addAll(migrationProblems);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		monitor.beginTask("Analyzing files", -1);

		walkFiles(projectDir, problems, monitor);

		monitor.done();

		final MigrationListener[] listeners = _migrationListenerTracker
				.getServices(new MigrationListener[0]);

		for (MigrationListener listener : listeners) {
			try {
				listener.problemsFound(problems);
			} catch (Exception e) {
				// ignore
			}
		}

		return problems;
	}

	@Override
	public void reportProblems(List<Problem> problems, int detail, String format, Object... args) {
		Reporter reporter = null;

		try {
			Collection<ServiceReference<Reporter>> references = this._context.getServiceReferences(Reporter.class, "(format=" + format + ")");

			if( references.size() > 0 ) {
				reporter = this._context.getService(references.iterator().next());
			}
			else {
				ServiceReference<Reporter> sr = this._context.getServiceReference(Reporter.class);
				reporter = this._context.getService(sr);
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

		OutputStream fos = null;

		if (args != null && args.length > 0) {
			if(args[0] instanceof File) {
				File outputFile = (File) args[0];
				try {
					outputFile.getParentFile().mkdirs();
					outputFile.createNewFile();
					fos = new FileOutputStream(outputFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(args[0] instanceof OutputStream) {
				fos = (OutputStream) args[0];
			}
		}

		if (problems.size() != 0) {
			reporter.beginReporting(detail, fos);

			for (Problem problem : problems) {
				reporter.report(problem);
			}

			reporter.endReporting();
		}
	}

	private void walkFiles(final File dir, final List<Problem> problems, final ProgressMonitor monitor) {
		final FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(
					Path path, BasicFileAttributes attrs)
				throws IOException {

				if (monitor.isCanceled()) {
					return FileVisitResult.TERMINATE;
				}

				File file = path.toFile();

				if (file.isFile())
				{
					String fileName = file.toPath().getFileName().toString();
					String extension = fileName.substring(
						fileName.lastIndexOf('.')+1);

					monitor.setTaskName("Analyzing file " + fileName);

					ServiceReference<FileMigrator>[] fileMigrators = _fileMigratorTracker.getServiceReferences();

					if(fileMigrators != null && fileMigrators.length > 0) {
						for (ServiceReference<FileMigrator> fm : fileMigrators) {
							if (monitor.isCanceled()) {
								return FileVisitResult.TERMINATE;
							}

							final List<String> fileExtensions = Arrays.asList(
									((String) fm.getProperty("file.extensions"))
											.split(","));

							if (fileExtensions != null && fileExtensions.contains(extension)) {
								final FileMigrator fmigrator = _context.getService(fm);

								try {
									final List<Problem> fileProblems = fmigrator.analyze(
										file);

									if ( fileProblems != null &&
										fileProblems.size() > 0) {

										problems.addAll(fileProblems);
									}
								}
								catch (Exception e) {
									e.printStackTrace();
								}

								_context.ungetService(fm);
							}
						}
					}
				}

				return super.visitFile(path, attrs);
			}
		};

		try {
			Files.walkFileTree(dir.toPath(), visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BundleContext _context;
	private ServiceTracker<MigrationListener, MigrationListener> _migrationListenerTracker;
	private ServiceTracker<FileMigrator, FileMigrator> _fileMigratorTracker;
	private ServiceTracker<ProjectMigrator, ProjectMigrator> _projectMigratorTracker;

}