package com.liferay.blade.java.provider;

import java.io.File;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JSPFile;
import com.liferay.blade.java.api.JavaFile;

@Component(
	property = {
		"file.extension=jspf"
	},
	service = {
		JavaFile.class,
		JSPFile.class
	}
)
public class JSPFFileWTP extends JSPFileWTP {

	public JSPFFileWTP() {
	}

	public JSPFFileWTP(File file) {
		super(file);
	}

}