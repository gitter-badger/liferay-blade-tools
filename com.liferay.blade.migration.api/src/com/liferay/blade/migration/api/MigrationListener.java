package com.liferay.blade.migration.api;

import java.util.List;

import org.osgi.annotation.versioning.ConsumerType;

import com.liferay.blade.java.api.Problem;

@ConsumerType
public interface MigrationListener {

	public void problemsFound(List<Problem> problems);

}
