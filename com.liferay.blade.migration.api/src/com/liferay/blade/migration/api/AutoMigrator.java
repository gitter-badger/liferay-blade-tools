package com.liferay.blade.migration.api;

import java.io.File;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.blade.java.api.Problem;

@ProviderType
public interface AutoMigrator {

	public void correctProblems(File file, List<Problem> problems) throws AutoMigrateException;

}
