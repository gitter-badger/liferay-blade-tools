package com.liferay.blade.migration.api;

import java.io.File;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.api.ProgressMonitor;

@ProviderType
public interface Migration {

	int DETAIL_LONG = 1 << 2;

	int DETAIL_SHORT = 1 << 1;

	public List<Problem> findProblems(File projectDir, ProgressMonitor monitor);

	public void reportProblems(List<Problem> problems, int detail, String format, Object... args);

}