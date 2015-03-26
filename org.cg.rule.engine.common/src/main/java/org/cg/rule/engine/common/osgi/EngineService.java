/**
 * 
 */
package org.cg.rule.engine.common.osgi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.dao.core.api.IApplication;
import org.cg.rule.engine.common.context.RealtimeRuleEngineContext;
import org.cg.rule.engine.common.context.RuleEngineContext;
import org.eclipse.core.runtime.CoreException;

import com.google.common.base.Throwables;

/**
 * A Engine Service that starts a real time rule engine
 * @author WZ
 * 
 */
public class EngineService implements IApplication {
 
	private final Log LOG = LogFactory.getLog(getClass());
	private RuleEngineContext ruleEngineContext = null;

	/**
	 * 
	 */
	public EngineService() {
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cg.dao.core.api.Service#start()
	 */
	public void start() throws CoreException {		
		try {
			ruleEngineContext = RealtimeRuleEngineContext.getInstance();		
			LOG.info("[start]real time rule engine initialized");
		} catch (Exception e) {
			LOG.error(Throwables.getStackTraceAsString(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cg.dao.core.api.Service#stop()
	 */
	public void stop() throws CoreException {
		if ( ruleEngineContext!=null ) {
			ruleEngineContext.destroyContext();
		}
	}
}
