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
import com.liferay.blade.migration.liferay70.apichanges.PortletConfigGetFormatMethods;

@SuppressWarnings("restriction")
public class PortletConfigGetFormatMethodsTest {

	final File liferayPortletFile = new File( "projects/filetests/LiferayPortlet.java" );
	final File unicodeLanguageImplFile = new File( "projects/filetests/UnicodeLanguageImpl.java" );
	PortletConfigGetFormatMethods component;

	@Before
	public void beforeTest() {
		assertTrue(liferayPortletFile.exists());
		assertTrue(unicodeLanguageImplFile.exists());
		component = new PortletConfigGetFormatMethods();
	}

	@Test
	public void unicodeLanguageImplFile() throws Exception {
		List<SearchResult> results = component.searchFile(unicodeLanguageImplFile,
				new JavaFileJDT(unicodeLanguageImplFile));

        assertNotNull(results);
        assertEquals(6, results.size());
	}

	@Test
	public void liferayPortletFile() throws Exception {
		List<SearchResult> results = component.searchFile(liferayPortletFile,
				new JavaFileJDT(liferayPortletFile));

        assertNotNull(results);
        assertEquals(3, results.size());
	}

}
