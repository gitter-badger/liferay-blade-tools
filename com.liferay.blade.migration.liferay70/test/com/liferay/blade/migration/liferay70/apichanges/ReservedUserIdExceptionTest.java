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
import com.liferay.blade.migration.liferay70.apichanges.ReservedUserIdExceptionCatch;

@SuppressWarnings("restriction")
public class ReservedUserIdExceptionTest {

	final File testFile = new File("projects/filetests/ReservedUserIdException.java");
	ReservedUserIdExceptionCatch component;

	@Before
	public void beforeTest() {
		assertTrue(testFile.exists());
		component = new ReservedUserIdExceptionCatch();
	}

	@Test
	public void reservedUserIdExceptionJavaTest() throws Exception {
		List<SearchResult> results = component.searchFile(testFile, new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(1, results.size());
	}

}
