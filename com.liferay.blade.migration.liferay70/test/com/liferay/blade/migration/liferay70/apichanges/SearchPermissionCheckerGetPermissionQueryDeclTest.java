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
import com.liferay.blade.migration.liferay70.apichanges.SearchPermissionCheckerGetPermissionQueryDecl;

@SuppressWarnings("restriction")
public class SearchPermissionCheckerGetPermissionQueryDeclTest {
	final File testFile = new File( "projects/filetests/SearchPermissionCheckerImpl.java" );
	SearchPermissionCheckerGetPermissionQueryDecl component;

	@Before
	public void beforeTest() {
		assertTrue( testFile.exists() );
		component = new SearchPermissionCheckerGetPermissionQueryDecl();
	}

    @Test
    public void searchPermissionCheckerTest() throws Exception {
    	List<SearchResult> results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(1, results.size());
    }

    @Test
    public void asearchPermissionCheckerTestTwice() throws Exception {
    	List<SearchResult> results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		results = component.searchFile(testFile,
				new JavaFileJDT(testFile));

		assertNotNull(results);
		assertEquals(1, results.size());
    }
}
