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
		"problem.title=Removed mbMessages and fileEntryTuples Attributes from app-view-search-entry Tag",
		"problem.section=#removed-mbmessages-and-fileentrytuples-attributes-from-app-view-search-entry-tag",
		"problem.summary=Removed mbMessages and fileEntryTuples Attributes from app-view-search-entry Tag",
		"problem.tickets=LPS-55886",
	},
	service = FileMigrator.class
)
public class AppViewSearchEntryTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JSPFile jspFileChecker) {

		return jspFileChecker.findJSPTags("liferay-ui:app-view-search-entry",
				new String[] { "mbMessages", "fileEntryTuples" }, null);
	}
}