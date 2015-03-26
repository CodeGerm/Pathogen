package org.cg.rule.engine.common.session;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.osgi.Activator;
import org.eclipse.core.runtime.FileLocator;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.osgi.framework.BundleContext;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

/**
 * a stateless kiesession
 * @author WZ
 *
 */
public class StatelessKieSession extends KieSessionBase {

	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(StatelessKieSession.class);
	
	private org.kie.api.runtime.StatelessKieSession ksession;
			
	public void init () {
		LOG.info("[init]preparing stateless session...");
		KieServices ks = KieServices.Factory.get();
		
		String path = "";
		try {
			//OSGi support
			BundleContext bc = Activator.getBundleContext();
			if ( bc != null ) {
				Preconditions.checkNotNull(bc, "bundle not activated");
				URL url = bc.getBundle().getEntry(settingPath);
				path = FileLocator.toFileURL(url).getPath();
			} else {
				path = settingPath;
			}
		} catch (Exception e) {
			LOG.error("Failed to locate file:");
			LOG.error(Throwables.getStackTraceAsString(e));
		}
		
		System.setProperty("kie.maven.settings.custom", path);
		ReleaseId releaseId = ks.newReleaseId(groupId, artifactId, version);
		LOG.info(String.format("[init]initializing kie container with releaseId %s", releaseId));
		
		KieContainer kieContainer = ks.newKieContainer(releaseId);
		KieScanner kieScanner = ks.newKieScanner(kieContainer);
		kieScanner.scanNow();
		
		ksession = kieContainer.newStatelessKieSession(session);
		ksession.setGlobal("LOG", LOG);

		LOG.info("[init]stateless rule engine prepared");
	}
	
	public org.kie.api.runtime.StatelessKieSession getKieSession() {
		return ksession;
	}

}
