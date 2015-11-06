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
		"problem.summary=Replaced Method getFacetQuery with getFacetBooleanFilter in Indexer",
		"problem.tickets=LPS-56064",
		"problem.title=Indexer API Changes",
		"problem.section=#replaced-method-getpermissionquery-with-getpermissionfilter-in-searchpermissionchecker-and-getfacetquery-with-getfacetbooleanfilter-in-indexer"
	},
	service = FileMigrator.class
)
public class IndexerGetFacetQuery extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		final List<SearchResult> searchResults = new ArrayList<>();

		final List<SearchResult> declaration = javaFileChecker.findMethodDeclaration("getFacetQuery",
				new String[] { "String", "SearchContextPortletURL" });

		searchResults.addAll(declaration);

		final List<SearchResult> invocations = javaFileChecker.findMethodInvocations("Indexer", null, "getFacetQuery",
				null);

		searchResults.addAll(invocations);

		return searchResults;
	}
}