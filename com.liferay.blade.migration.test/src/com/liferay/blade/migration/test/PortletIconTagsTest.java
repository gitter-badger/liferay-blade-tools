package com.liferay.blade.migration.test;

import static org.junit.Assert.assertEquals;

import com.liferay.blade.migration.api.Migration;
import com.liferay.blade.java.api.Problem;
import com.liferay.blade.java.util.NullProgressMonitor;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class PortletIconTagsTest {

	@Test
	public void findProblems() throws Exception {
		ServiceReference<Migration> sr = context
				.getServiceReference(Migration.class);

		Migration m = context.getService(sr);

		List<Problem> problems = m.findProblems(new File("jsptests/portlet-icon/"), new NullProgressMonitor());

		assertEquals(13, problems.size());

	}

	private final BundleContext context = FrameworkUtil.getBundle(
		this.getClass()).getBundleContext();

}