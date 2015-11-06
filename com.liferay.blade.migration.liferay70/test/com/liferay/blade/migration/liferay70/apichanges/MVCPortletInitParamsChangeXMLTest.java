package com.liferay.blade.migration.liferay70.apichanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.java.provider.XMLFileSAX;
import com.liferay.blade.migration.liferay70.apichanges.MVCPortletInitParamsChangeXML;

/**
 * @author Andy Wu
 */
@SuppressWarnings("restriction")
public class MVCPortletInitParamsChangeXMLTest {

	@Before
	public void setUp() {
		assertTrue(testFile.exists());
		component = new MVCPortletInitParamsChangeXML();
	}

	@Test
	public void testMVCPortletChangeXMLTest() throws Exception {
		List<SearchResult> problems = component.searchFile(testFile, new XMLFileSAX(testFile));

		assertNotNull(problems);
		assertEquals(5, problems.size());
	}

	private MVCPortletInitParamsChangeXML component;
	private final File testFile = new File(
		"projects/knowledge-base-portlet-6.2.x/docroot/WEB-INF/portlet.xml");

}
