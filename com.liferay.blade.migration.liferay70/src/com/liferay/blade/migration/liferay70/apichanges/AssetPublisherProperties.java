package com.liferay.blade.migration.liferay70.apichanges;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.migration.api.FileMigrator;

@Component(
	property = {
		"file.extensions=properties",
		"problem.title=Asset Publisher Properties Removed",
		"problem.summary=Removed the asset.publisher.asset.entry.query.processors Property",
		"problem.tickets=LPS-52966",
		"problem.section=#removed-the-asset",
	},
	service = FileMigrator.class
)
public class AssetPublisherProperties extends PropertiesFileMigrator {

	@Override
	protected void addPropertiesToSearch(List<String> _properties) {
		_properties.add("asset.publisher.asset.entry.query.processors");
	}

}
