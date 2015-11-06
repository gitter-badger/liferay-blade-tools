package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JavaFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JavaFileMigrator;

@Component(
	property = {
		"file.extensions=java,jsp,jspf",
		"problem.title=Methods removed in SearchResult API",
		"problem.section=#removed-mbmessages-and-fileentrytuples-attributes-from-app",
		"problem.summary=Removed getMbMessages , getFileEntryTuples and addMbMessage Methods from SearchResult Class",
		"problem.tickets=LPS-55886",
	},
	service = FileMigrator.class
)
public class SearchResultInvocation extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		final List<SearchResult> results = new ArrayList<SearchResult>();

		results.addAll(javaFileChecker.findMethodInvocations("SearchResult",
				null, "getMBMessages", null));

		results.addAll(javaFileChecker.findMethodInvocations("SearchResult",
				null, "getFileEntryTuples", null));

		results.addAll(javaFileChecker.findMethodInvocations("SearchResult",
				null, "addMBMessage", new String[] { "MBMessage" }));

		return results;
	}

}
