package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JavaFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JavaFileMigrator;

@Component(
	property = {
		"file.extensions=java,jsp,jspf",
		"problem.summary=The field type from the Journal Article entity has been removed. ",
		"problem.title=Migration of the Field Type from the Journal Article API into a Vocabulary" +
			"The Journal API no longer supports this parameter. A new vocabulary " +
			"called Web Content Types is created when migrating from previous " +
			"versions of Liferay, and the types from the existing articles are kept " +
			"as categories of this vocabulary.",
		"problem.tickets=LPS-50764",
		"problem.section=#migration-of-the-field-type-from-the-journal-article-api-into-a-vocabulary"
	},
	service = FileMigrator.class
)
public class WebContentTypeRemoved extends JavaFileMigrator {

	@Override
	protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {

		final List<SearchResult> searchResults = new ArrayList<>();
		// check JournalArticle.getType() and JournalFeed.getType()
		List<SearchResult> getTypes = javaFileChecker.findMethodInvocations(
				"JournalArticle", null, "getType", null);
		searchResults.addAll(getTypes);

		getTypes = javaFileChecker.findMethodInvocations("JournalFeed", null,
				"getType", null);
		searchResults.addAll(getTypes);

		// callers of ArticleTypeException's methods
		SearchResult exceptionImports = javaFileChecker
				.findImport("com.liferay.portlet.journal.ArticleTypeException");

		if (exceptionImports != null) {
			searchResults.add(exceptionImports);
		}

		List<SearchResult> catchExceptions = javaFileChecker
				.findCatchExceptions(new String[] { "ArticleTypeException" });
		searchResults.addAll(catchExceptions);

		// JournalArticleLocalServiceUtil
		List<SearchResult> journalArticleLocalServiceUtil = javaFileChecker
				.findMethodInvocations(null, "JournalArticleLocalServiceUtil",
						"addArticle", new String[] { "long", "long", "long",
								"long", "long", "String", "boolean", "double",
								"Map<Locale,String>", "Map<Locale,String>",
								"String", "String", "String", "String",
								"String", "int", "int", "int", "int", "int",
								"int", "int", "int", "int", "int", "boolean",
								"int", "int", "int", "int", "int", "boolean",
								"boolean", "boolean", "String", "File",
								"Map<String,byte[]>", "String",
								"ServiceContext" });

		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "search", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "Date", "Date",
						"int", "Date", "int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "search", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String", "String", "Date", "Date", "int", "Date",
						"boolean", "int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "search", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String[]", "String[]", "Date", "Date", "int", "Date",
						"boolean", "int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "search", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"String", "String", "String", "String", "String",
						"String", "String", "LinkedHashMap<String,Object>",
						"boolean", "int", "int", "Sort" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "searchCount",
				new String[] { "long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "Date", "Date",
						"int", "Date" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "searchCount",
				new String[] { "long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String", "String", "Date", "Date", "int", "Date",
						"boolean" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "searchCount",
				new String[] { "long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String[]", "String[]", "Date", "Date", "int", "Date",
						"boolean" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		journalArticleLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalArticleLocalServiceUtil", "updateArticle",
				new String[] { "long", "long", "long", "String", "double",
						"Map<Locale,String>", "Map<Locale,String>", "String",
						"String", "String", "String", "String", "int", "int",
						"int", "int", "int", "int", "int", "int", "int", "int",
						"boolean", "int", "int", "int", "int", "int",
						"boolean", "boolean", "boolean", "String", "File",
						"Map<String,byte[]>", "String", "ServiceContext" });
		searchResults.addAll(journalArticleLocalServiceUtil);

		// JournalArticleServiceUtil
		List<SearchResult> journalArticleServiceUtil = javaFileChecker
				.findMethodInvocations(null, "JournalArticleServiceUtil",
						"addArticle", new String[] { "long", "long", "long",
								"long", "String", "boolean",
								"Map<Locale,String>", "Map<Locale,String>",
								"String", "String", "String", "String",
								"String", "int", "int", "int", "int", "int",
								"int", "int", "int", "int", "int", "boolean",
								"int", "int", "int", "int", "int", "boolean",
								"boolean", "boolean", "String", "File",
								"Map<String,byte[]>", "String",
								"ServiceContext" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "addArticle", new String[] {
						"long", "long", "long", "long", "String", "boolean",
						"Map<Locale,String>", "Map<Locale,String>", "String",
						"String", "String", "String", "String", "int", "int",
						"int", "int", "int", "int", "int", "int", "int", "int",
						"boolean", "int", "int", "int", "int", "int",
						"boolean", "boolean", "String", "ServiceContext" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "search", new String[] { "long",
						"long", "List<Long>", "long", "String", "Double",
						"String", "String", "String", "Date", "Date", "int",
						"Date", "int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "search", new String[] { "long",
						"long", "List<Long>", "long", "String", "Double",
						"String", "String", "String", "String", "String",
						"String", "Date", "Date", "int", "Date", "boolean",
						"int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "search", new String[] { "long",
						"long", "List<Long>", "long", "String", "Double",
						"String", "String", "String", "String", "String[]",
						"String[]", "Date", "Date", "int", "Date", "boolean",
						"int", "int", "OrderByComparator" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "searchCount", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "Date", "Date",
						"int", "Date" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "searchCount", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String", "String", "Date", "Date", "int", "Date",
						"boolean" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "searchCount", new String[] {
						"long", "long", "List<Long>", "long", "String",
						"Double", "String", "String", "String", "String",
						"String[]", "String[]", "Date", "Date", "int", "Date",
						"boolean" });
		searchResults.addAll(journalArticleServiceUtil);

		journalArticleServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalArticleServiceUtil", "updateArticle", new String[] {
						"long", "long", "String", "double",
						"Map<Locale,String>", "Map<Locale,String>", "String",
						"String", "String", "String", "String", "int", "int",
						"int", "int", "int", "int", "int", "int", "int", "int",
						"boolean", "int", "int", "int", "int", "int",
						"boolean", "boolean", "boolean", "String", "File",
						"Map<String,byte[]>", "String", "ServiceContext" });
		searchResults.addAll(journalArticleServiceUtil);

		// JournalFeedLocalServiceUtil
		List<SearchResult> journalFeedLocalServiceUtil = javaFileChecker
				.findMethodInvocations(null, "JournalFeedLocalServiceUtil",
						"addFeed", new String[] { "long", "long", "String",
								"boolean", "String", "String", "String",
								"String", "String", "String", "int", "String",
								"String", "String", "String", "String",
								"String", "double", "ServiceContext" });
		searchResults.addAll(journalFeedLocalServiceUtil);

		journalFeedLocalServiceUtil = javaFileChecker.findMethodInvocations(
				null, "JournalFeedLocalServiceUtil", "updateFeed",
				new String[] { "long", "String", "String", "String", "String",
						"String", "String", "String", "int", "String",
						"String", "String", "String", "String", "String",
						"double", "ServiceContext" });
		searchResults.addAll(journalFeedLocalServiceUtil);

		// JournalFeedServiceUtil
		List<SearchResult> journalFeedServiceUtil = javaFileChecker
				.findMethodInvocations(null, "JournalFeedServiceUtil",
						"addFeed", new String[] { "long", "String", "boolean",
								"String", "String", "String", "String",
								"String", "String", "int", "String", "String",
								"String", "String", "String", "String",
								"double", "ServiceContext" });
		searchResults.addAll(journalFeedServiceUtil);

		journalFeedServiceUtil = javaFileChecker.findMethodInvocations(null,
				"JournalFeedServiceUtil", "updateFeed", new String[] { "long",
						"String", "String", "String", "String", "String",
						"String", "String", "int", "String", "String",
						"String", "String", "String", "String", "double",
						"ServiceContext" });
		searchResults.addAll(journalFeedServiceUtil);

		return searchResults;
	}

}
