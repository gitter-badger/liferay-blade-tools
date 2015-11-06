package com.liferay.blade.migration.liferay70.apichanges;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.migration.api.AutoMigrator;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.ImportStatementMigrator;

@Component(
	property = {
		"file.extensions=java",
		"problem.title=Moved the Contact Name Exception Classes to Inner Classes of ContactNameException",
		"problem.summary=The use of classes ContactFirstNameException, ContactFullNameException, and ContactLastNameException has been moved to inner classes in a new class called ContactNameException.",
		"problem.tickets=LPS-55364",
		"problem.section=#moved-the-contact-name-exception-classes-to-inner-classes-of-contactnameexception",
		"auto.correct=import"
	},
	service = {
		FileMigrator.class,
		AutoMigrator.class
	}
)
public class ContactNameExceptionImport extends ImportStatementMigrator {

	private final static String[] IMPORTS = new String[] {
		"com.liferay.portal.ContactFirstNameException",
		"com.liferay.portal.ContactFullNameException",
		"com.liferay.portal.ContactLastNameException"
	};

	private final static String[] IMPORTS_FIXED = new String[] {
		"com.liferay.portal.ContactFirstNameException",
		"com.liferay.portal.ContactFullNameException",
		"com.liferay.portal.ContactLastNameException"
	};

	public ContactNameExceptionImport() {
		super(IMPORTS, IMPORTS_FIXED);
	}

}
