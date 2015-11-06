package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JSPFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JSPFileMigrator;

@Component(
	property = {
		"file.extensions=jsp,jspf",
		"problem.title=Removed the liferay-ui:journal-article tag",
		"problem.section=#removed-the-liferay-uijournal-article-tag",
		"problem.summary=Removed the liferay-ui:journal-article Tag",
		"problem.tickets=LPS-56383",
	},
	service = FileMigrator.class
)
public class JournalArticleTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file,
			JSPFile jspFileChecker) {

		return jspFileChecker.findJSPTags("liferay-ui:journal-article", null , null);
	}
}