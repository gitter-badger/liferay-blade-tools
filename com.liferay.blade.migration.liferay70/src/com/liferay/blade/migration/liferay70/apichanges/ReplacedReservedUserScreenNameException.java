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
		"problem.title=Replaced the ReservedUserScreenNameException with UserScreenNameException.MustNotBeReserved in UserLocalService",
		"problem.section=#replaced-the-reserveduseremailaddressexception-with-useremailaddressexception-inner-classes-in-user-services",
		"problem.summary=Replaced the ReservedUserScreenNameException with UserScreenNameException.MustNotBeReserved in UserLocalService",
		"problem.tickets=LPS-53113",
	},
	service = FileMigrator.class
)
public class ReplacedReservedUserScreenNameException extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findCatchExceptions(new String[] {"ReservedUserScreenNameException"});
	}
}