
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
		"problem.title=Removed WikiUtil.getEntries Method",
		"problem.section=#removed-wikiutil",
		"problem.summary=Removed WikiUtil.getEntries Method",
		"problem.tickets=LPS-56242",
	},
	service = FileMigrator.class
)
public class WikiUtilGetEntriesInvocation extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findMethodInvocations(null, "WikiUtil",
				"getEntries", null);
	}

}
