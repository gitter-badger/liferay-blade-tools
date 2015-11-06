package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JavaFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JavaFileMigrator;

@Component(
	property = {
		"file.extensions=java",
		"problem.title=Changes in Exceptions Thrown by User Services",
		"problem.section=#changes-in-exceptions-thrown-by-user-services",
		"problem.summary=Changes in Exceptions Thrown by User Services",
		"problem.tickets=LPS-47130",
	},
	service = FileMigrator.class
)
public class UserServicesThrownExceptionsChanges extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findCatchExceptions(new String[] {"DuplicateUserScreenNameException", "DuplicateUserEmailAddressException"});
	}
}