package com.liferay.blade.migration.liferay70.apichanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.java.provider.JavaFileJDT;
import com.liferay.blade.migration.liferay70.apichanges.AssetRendererGetSummaryDecl;

@SuppressWarnings("restriction")
public class AssetRendererGetSummaryDeclTest {

	final File testFile = new File(
			"projects/knowledge-base-portlet-6.2.x/docroot/WEB-INF/src/com/liferay/knowledgebase/admin/asset/KBArticleAssetRenderer.java");
	AssetRendererGetSummaryDecl component;

	@Before
	public void beforeTest() {
		assertTrue(testFile.exists());
		component = new AssetRendererGetSummaryDecl();
	}

	@Test
	public void assetRendererAPIsAnalyzeTest() throws Exception {
		List<SearchResult> results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(1, results.size());
	}

	@Test
	public void assetRendererAPIsAnalyzeTestTwice() throws Exception {
		List<SearchResult> results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(1, results.size());
	}
}
