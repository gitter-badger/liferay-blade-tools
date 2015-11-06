package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.java.api.XMLFile;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.XMLFileMigrator;

@Component(
	property = {
		"file.extensions=xml",
		"problem.title=The build-service task must be executed to regenerate code",
		"problem.summary=",
		"problem.tickets=",
		"problem.section="
	},
	service = FileMigrator.class
)
public class RunServiceBuilderRequired extends XMLFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, XMLFile xmlFile) {
		if (!"service.xml".equals(file.getName())) {
			return Collections.emptyList();
		}

//		final XMLFileChecker xmlFileChecker = new XMLFileChecker(file);

//		final List<SearchResult> results = new ArrayList<>();

//		results.addAll(xmlFileChecker.findTag("service-builder", null));

		return Collections.singletonList(new SearchResult(file, 0, 0, 1, 1, false));
	}

	@Override
	public List<Problem> analyze(File file) {
		final List<Problem> problems = super.analyze(file);

		for (Problem problem : problems) {
			problem.html = "In order to compile this project for 7.0, the build-service task must be executed once to regenerate all the code for version 7.0 of Liferay.";
		}

		return problems;
	}

}
