package com.liferay.blade.migration.api;

@SuppressWarnings("serial")
public class AutoMigrateException extends Exception {

	public AutoMigrateException(String msg) {
		super(msg);
	}

	public AutoMigrateException(String msg, Exception e) {
		super(msg, e);
	}

}
