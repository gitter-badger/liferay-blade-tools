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
import com.liferay.blade.migration.liferay70.apichanges.ShoppingCartLegacyAPI;

@SuppressWarnings("restriction")
public class ShoppingCartLegacyAPITest {
	final File testFile = new File(
			"projects/legacy-apis-ant-portlet/docroot/WEB-INF/src/com/liferay/CartAction.java");
	ShoppingCartLegacyAPI component;

	@Before
	public void beforeTest() {
		assertTrue(testFile.exists());
		component = new ShoppingCartLegacyAPI();
	}

	@Test
	public void shoppingCartLegacyAPITest() throws Exception {
		List<SearchResult> results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(4, results.size());

		SearchResult problem = results.get(0);

		assertEquals(32, problem.startLine);
		assertEquals(1444, problem.startOffset);
		assertEquals(1509, problem.endOffset);

		problem = results.get(1);

		assertEquals(143, problem.startLine);
		assertEquals(4549, problem.startOffset);
		assertEquals(4714, problem.endOffset);

		problem = results.get(2);

		assertEquals(33, problem.startLine);
		assertEquals(1518, problem.startOffset);
		assertEquals(1583, problem.endOffset);

		problem = results.get(3);

		assertEquals(118, problem.startLine);
		assertEquals(3870, problem.startOffset);
		assertEquals(3914, problem.endOffset);

	}

}
