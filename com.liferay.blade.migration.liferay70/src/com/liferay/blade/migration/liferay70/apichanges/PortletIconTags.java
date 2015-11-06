package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JSPFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JSPFileMigrator;

@Component(
	property = {
		"file.extensions=jsp,jspf",
		"problem.title=Removed the Tags that Start with portlet:icon-",
		"problem.section=#removed-the-tags-that-start-with-portleticon-",
		"problem.summary=Removed the Tags that Start with portlet:icon-",
		"problem.tickets=LPS-54620",
	},
	service = FileMigrator.class
)
public class PortletIconTags extends JSPFileMigrator {

	private final static String[] jspTags = new String[]{
		"liferay-portlet:icon-close",
		"liferay-portlet:icon-configuration",
		"liferay-portlet:icon-edit",
		"liferay-portlet:icon-edit-defaults",
		"liferay-portlet:icon-edit-guest",
		"liferay-portlet:icon-export-import",
		"liferay-portlet:icon-help",
		"liferay-portlet:icon-maximize",
		"liferay-portlet:icon-minimize",
		"liferay-portlet:icon-portlet-css",
		"liferay-portlet:icon-print",
		"liferay-portlet:icon-refresh",
		"liferay-portlet:icon-staging"
	};

	@Override
	protected List<SearchResult> searchFile(File file,
			JSPFile jspFileChecker) {

		final List<SearchResult> searchResults = new ArrayList<SearchResult>();

		for (String jspTag : jspTags) {
			final List<SearchResult> jspTagResults = jspFileChecker
					.findJSPTags(jspTag, null, null);

			if (jspTagResults.size() != 0) {
				searchResults.addAll(jspTagResults);
			}
		}

		return searchResults;
	}
}