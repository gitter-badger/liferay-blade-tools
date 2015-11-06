package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;

import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.MarkdownParser;
import com.liferay.blade.migration.liferay70.PropertiesFileChecker;

public abstract class PropertiesFileMigrator implements FileMigrator {

	ComponentContext _context;
	String _problemTitle;
	String _problemSummary;
	String _problemType;
	String _problemTickets;
	String _sectionKey = "";
	final List<String> _properties = new ArrayList<String>();

	@Activate
	public void activate(ComponentContext ctx) {
		_context = ctx;

		final Dictionary<String, Object> properties =
			_context.getProperties();

		_problemTitle = (String)properties.get("problem.title");
		_problemSummary = (String)properties.get("problem.summary");
		_problemType = (String)properties.get("file.extensions");
		_problemTickets = (String)properties.get("problem.tickets");
		_sectionKey = (String)properties.get("problem.section");

		addPropertiesToSearch(this._properties);
	}

	protected abstract void addPropertiesToSearch(List<String> _properties);

	@Override
	public List<Problem> analyze(File file) {
		final List<Problem> problems = new ArrayList<>();

		PropertiesFileChecker propertiesFileChecker =
			new PropertiesFileChecker(file);

		for (String key : _properties) {
			List<SearchResult> results =
				propertiesFileChecker.findProperties(key);

			if (results != null) {
				String sectionHtml = MarkdownParser.getSection("BREAKING_CHANGES.markdown", _sectionKey);

				for (SearchResult searchResult : results) {
					problems.add(new Problem( _problemTitle, _problemSummary,
						_problemType, _problemTickets, file,
						searchResult.startLine, searchResult.startOffset,
						searchResult.endOffset, sectionHtml, searchResult.autoCorrectContext));
				}
			}
		}

		return problems;
	}

}
