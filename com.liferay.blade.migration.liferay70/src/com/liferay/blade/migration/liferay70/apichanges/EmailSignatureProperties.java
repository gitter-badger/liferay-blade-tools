package com.liferay.blade.migration.liferay70.apichanges;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.migration.api.FileMigrator;

@Component(
	property = {
		"file.extensions=properties",
		"problem.title=Email Signature Properties",
		"problem.summary=Merged Configured Email Signature Field into the Body of Email Messages from Message Boards and Wiki",
		"problem.tickets=LPS-44599",
		"problem.section=#merged-configured-email-signature-field-into-the-body-of-email-messages-from-message-boards-and-wiki",
	},
	service = FileMigrator.class
)
public class EmailSignatureProperties extends PropertiesFileMigrator {

	@Override
	protected void addPropertiesToSearch(List<String> properties) {
		properties.add("message.boards.email.message.added.signature");
		properties.add("message.boards.email.message.updated.signature");
		properties.add("wiki.email.page.added.signature");
		properties.add("wiki.email.page.updated.signature");
	}

}
