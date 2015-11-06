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
		"problem.title=Renamed URI Attribute Used to Generate AUI Tag Library",
		"problem.section=#renamed-uri-attribute-used-to-generate-aui-tag-library",
		"problem.summary=We should use the new AUI URI declaration:http://liferay.com/tld/aui",
		"problem.tickets=LPS-57809",
	},
	service = FileMigrator.class
)
public class AUILiferayTags extends JSPFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JSPFile jspFileChecker) {
		return jspFileChecker.findJSPTags("jsp:directive.taglib",
				new String[] { "uri" },new String[] { "http://alloy.liferay.com/tld/aui" });
	}
}