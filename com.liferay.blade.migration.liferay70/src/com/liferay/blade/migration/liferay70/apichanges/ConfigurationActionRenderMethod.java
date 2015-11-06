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
		"file.extensions=java",
		"problem.summary=Removed render Method from ConfigurationAction API",
		"problem.tickets=LPS-56300",
		"problem.title=ConfigurationAction render method",
		"problem.section=#removed-render-method-from-configurationaction-api"
	},
	service = FileMigrator.class
)
public class ConfigurationActionRenderMethod extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		final List<SearchResult> searchResults = new ArrayList<>();

		// render method declarations
		List<SearchResult> declarations = javaFileChecker.findMethodDeclaration("render",
				new String[] { "PortletConfig", "RenderRequest", "RenderResponse" });

		searchResults.addAll(declarations);

		// render method invocations
		List<SearchResult> invocations = javaFileChecker.findMethodInvocations(
				"ConfigurationAction", null, "render", null);

		searchResults.addAll(invocations);

		return searchResults;
	}
}