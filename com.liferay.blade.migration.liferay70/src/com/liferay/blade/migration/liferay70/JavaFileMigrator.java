package com.liferay.blade.migration.liferay70;

import com.liferay.blade.java.api.JavaFile;

public abstract class JavaFileMigrator extends AbstractFileMigrator<JavaFile> {

	public JavaFileMigrator() {
		super(JavaFile.class);
	}

}
