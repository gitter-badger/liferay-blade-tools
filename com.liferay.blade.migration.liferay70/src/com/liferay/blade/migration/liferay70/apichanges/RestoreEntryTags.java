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
		"problem.title=Changed the Usage of the liferay-ui:restore-entry Tag",
		"problem.section=#changed-the-usage-of-the-liferay-uirestore-entry-tag",
		"problem.summary=Changed the Usage of the liferay-ui:restore-entry Tag",
		"problem.tickets=LPS-54106",
	},
	service = FileMigrator.class
)
public class RestoreEntryTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file,
			JSPFile jspFileChecker) {

		return jspFileChecker.findJSPTags("liferay-ui:restore-entry", null, null);
	}
}