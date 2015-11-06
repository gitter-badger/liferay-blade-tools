package com.liferay.blade.java.provider;

import aQute.lib.io.IO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public class WorkspaceHelper {

	private void addNaturesToProject( IProject proj, String[] natureIds, IProgressMonitor monitor )
	        throws CoreException {
        IProjectDescription description = proj.getDescription();

        String[] prevNatures = description.getNatureIds();
        String[] newNatures = new String[prevNatures.length + natureIds.length];

        System.arraycopy( prevNatures, 0, newNatures, 0, prevNatures.length );

        for( int i = prevNatures.length; i < newNatures.length; i++ ) {
            newNatures[i] = natureIds[i - prevNatures.length];
        }

        description.setNatureIds( newNatures );
        proj.setDescription( description, monitor );
    }

	public IFile createIFile(String projectName, File file) throws CoreException, IOException {
		IJavaProject project = getJavaProject(projectName);

		IFile projectFile = project.getProject().getFile(file.getName());

		final IProgressMonitor npm = new NullProgressMonitor();

		if (projectFile.exists()) {
			projectFile.delete(IFile.FORCE, npm);
		}

		projectFile.create(new ByteArrayInputStream(IO.read(file)), IFile.FORCE, npm);

		return projectFile;
	}

	private IJavaProject getJavaProject(String projectName) throws CoreException {
		IProject javaProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		IProgressMonitor monitor = new NullProgressMonitor();

		if (!javaProject.exists()) {
			IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
			javaProject.create(monitor);
			javaProject.open(monitor);
			javaProject.setDescription(description, monitor);
		}

		javaProject.open(monitor);
		addNaturesToProject(javaProject, new String[] { JavaCore.NATURE_ID }, monitor);

		return JavaCore.create(javaProject);
	}

}
