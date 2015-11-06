package com.liferay.blade.migration.api;

import java.io.File;
import java.util.List;

import org.osgi.annotation.versioning.ConsumerType;

import com.liferay.blade.java.api.Problem;

@ConsumerType
public interface FileMigrator {

	public List<Problem> analyze(File file);

}