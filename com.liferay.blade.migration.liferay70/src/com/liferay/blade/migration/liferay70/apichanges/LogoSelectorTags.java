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
		"problem.title=liferay-ui:logo-selector Tag Parameter Changes",
		"problem.section=#the-liferay-uilogo-selector-tag-requires-parameter-changes",
		"problem.summary=Removed the editLogoURL of liferay-ui:logo-selector Tag",
		"problem.tickets=LPS-42645",
	},
	service = FileMigrator.class
)
public class LogoSelectorTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file,
			JSPFile jspFileChecker) {

		return jspFileChecker.findJSPTags("liferay-ui:logo-selector",
				new String[] { "editLogoURL" } , null);
	}
}