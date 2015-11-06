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
		"problem.title=Removed the getClassNamePortletId(String) Method from PortalUtil Class",
		"problem.section=#removed-the-getclassnameportletid",
		"problem.summary=Removed the getClassNamePortletId(String) Method from PortalUtil Class",
		"problem.tickets=LPS-50604",
	},
	service = FileMigrator.class
)
public class PortalUtilGetClassNamePortletIdInvocation extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findMethodInvocations(null, "PortalUtil",
				"getClassNamePortletId", null);
	}

}
