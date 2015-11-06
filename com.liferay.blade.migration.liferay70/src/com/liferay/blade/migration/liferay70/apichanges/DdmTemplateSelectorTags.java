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
		"problem.title=Changed Usage of the liferay-ui:ddm-template-selector Tag",
		"problem.section=#changed-usage-of-the-liferay-uiddm-template-selector-tag",
		"problem.summary=The attribute classNameId of the liferay-ui:ddm-template-selector taglib tag has been renamed className",
		"problem.tickets=LPS-53790",
	},
	service = FileMigrator.class
)
public class DdmTemplateSelectorTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file,
			JSPFile jspFileChecker) {

		return jspFileChecker.findJSPTags("liferay-ui:ddm-template-selector", new String[]{"classNameId"}, null);
	}
}