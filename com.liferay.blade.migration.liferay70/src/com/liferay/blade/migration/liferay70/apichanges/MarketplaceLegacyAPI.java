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
		"problem.summary=All Marketplace APIs previously exposed as Liferay Portal API in 6.2 have been move out from portal-service into separate OSGi modules",
		"problem.tickets=LPS-54165",
		"problem.title=Marketplace APIs migrated to OSGi module",
		"problem.section=#legacy"
	},
	service = FileMigrator.class
)
public class MarketplaceLegacyAPI extends JavaFileMigrator {

	private static final String[] SERVICE_API_PREFIXES =  {
		"com.liferay.marketplace.service.App",
		"com.liferay.marketplace.service.Module"
	};

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
		return javaFileChecker.findServiceAPIs(SERVICE_API_PREFIXES);
	}
}
