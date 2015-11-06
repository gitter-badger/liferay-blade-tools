package com.liferay.blade.migration.liferay70.apichanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.liferay.blade.java.api.Problem;
import com.liferay.blade.migration.liferay70.apichanges.UsersLastNameRequiredProperties;

public class UsersLastNameRequiredPropertiesTest {
	final File file = new File("projects/knowledge-base-portlet-6.2.x/docroot/WEB-INF/src/portal.properties");
	UsersLastNameRequiredProperties component;

	@Before
	public void beforeTest() {
		assertTrue(file.exists());
		component = new UsersLastNameRequiredProperties();
		component.addPropertiesToSearch(component._properties);
	}

	@Test
	public void usersLastNameRequiredPropertiesTest() throws Exception {
		List<Problem> problems = component.analyze(file);

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

	@Test
	public void portalPropertiesAnalyzeTest2() throws Exception {
		List<Problem> problems = component.analyze(file);
		problems = component.analyze(file);

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

}
