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
		"file.extensions=java,jsp,jspf",
		"problem.summary=Replaced Method getPermissionQuery with getPermissionFilter in SearchPermissionChecker",
		"problem.tickets=LPS-56064",
		"problem.title=Replaced Method getPermissionQuery",
		"problem.section=#replaced-method-getpermissionquery-with-getpermissionfilter-in-searchpermissionchecker-and-getfacetquery-with-getfacetbooleanfilter-in-indexer"
	},
	service = FileMigrator.class
)
public class SearchPermissionCheckerGetPermissionQueryDecl extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findMethodDeclaration(
				"getPermissionQuery",
				new String[] {"long", "long[]", "long", "String",
						"Query", "SearchContext"});
	}
}