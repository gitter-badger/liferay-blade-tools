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
		"problem.summary=DDM Structure Local Service API No Longer Has the updateXSDFieldMetadata operation",
		"problem.tickets=LPS-47559",
		"problem.title=DDM Structure Local Service API No Longer Has the updateXSDFieldMetadata operation",
		"problem.section=#ddm-structure-local-service-api-no-longer-has-the-updatexsdfieldmetadata-operation"
	},
	service = FileMigrator.class
)
public class DDMStructureLocalServiceAPI extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findMethodInvocations(null, "DDMStructureLocalServiceUtil", "updateXSDFieldMetadata",null);
	}
}