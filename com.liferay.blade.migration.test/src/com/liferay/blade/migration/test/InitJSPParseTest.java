package com.liferay.blade.migration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.liferay.blade.migration.api.Migration;
import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.util.NullProgressMonitor;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class InitJSPParseTest {

	@Test
	public void initParseErrorCheck() throws Exception {
		ServiceReference<Migration> sr = context
				.getServiceReference(Migration.class);

		Migration m = context.getService(sr);

		List<Problem> problems = m.findProblems(new File("jsptests/jukebox-portlet/"), new NullProgressMonitor());

		assertEquals(31, problems.size());

		boolean found = false;

		for (Problem problem : problems) {
			if (problem.file.getName().endsWith("view_search.jsp")) {
				if (problem.lineNumber == 109 && problem.startOffset == 3697 && problem.endOffset == 3752) {
					found = true;
				}
			}
		}

		if (!found) {
			fail();
		}
	}

	private final BundleContext context = FrameworkUtil.getBundle(
		this.getClass()).getBundleContext();

}