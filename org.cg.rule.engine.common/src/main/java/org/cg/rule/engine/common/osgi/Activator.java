/**
 * 
 */
package org.cg.rule.engine.common.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author WZ
 *
 */
public class Activator implements BundleActivator  {
	
	public static final String PLUGIN_ID = "org.cg.rule.engine.common";
	
	private volatile static Activator activator;

	private static BundleContext bundleContext;

	@Override
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext = null;
	}

	public static BundleContext getBundleContext() {
		return bundleContext;
	}

	public static Activator getDefault() {
		return activator;
	}
}
