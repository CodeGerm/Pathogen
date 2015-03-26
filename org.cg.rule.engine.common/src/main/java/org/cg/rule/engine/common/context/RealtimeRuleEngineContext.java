package org.cg.rule.engine.common.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.osgi.Activator;
import org.cg.rule.engine.common.provider.FactProvider;
import org.cg.rule.engine.common.sample.model.SampleFact;
import org.cg.rule.engine.common.source.FactSource;
import org.cg.rule.engine.common.source.SampleFactSource;
import org.springframework.context.ApplicationContext;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;

import com.google.common.base.Preconditions;

/**
 * implements a stateless rule engine
 * 
 * @author WZ
 */
public class RealtimeRuleEngineContext implements RuleEngineContext {
	
	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(RealtimeRuleEngineContext.class);
	
	private static volatile RealtimeRuleEngineContext instance = null;
	
	private volatile ApplicationContext context;
	
	private RealtimeRuleEngineContext() {
		LOG.info("Starting a stateless Rule Engine");
		try {
			String[] configLocations = {"classpath:applicationContext_Realtime.RuleEngine.xml"};
			OsgiBundleXmlApplicationContext osgiAppContext = new OsgiBundleXmlApplicationContext(configLocations);
			osgiAppContext.setClassLoader(osgiAppContext.getClassLoader());
			osgiAppContext.setBundleContext(Activator.getBundleContext());
			osgiAppContext.refresh();
			context = osgiAppContext;
		} catch (Exception e) {
			LOG.error("Failed to create spring context", e);
			this.destroyContext();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ApplicationContext getContext() {
		Preconditions.checkState(context!=null, "context is not intialized");
		return context;
	}

	/**
	 * get a StatelessRuleEngine instance
	 * @return
	 */
	public static RealtimeRuleEngineContext getInstance() {
		if ( instance!=null ){
			return instance;
		}
		synchronized (RealtimeRuleEngineContext.class) {
			if ( instance!=null ){
				return instance;
			}
			instance = new RealtimeRuleEngineContext();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroyContext() {		
		if ( context != null ) {
			((OsgiBundleXmlApplicationContext) context).close();
		}
		LOG.info("Spring context closed");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FactProvider getFactProvider(Class<?> factType) {
		Preconditions.checkState(context!=null, "context is not intialized");
		if ( factType.equals(SampleFact.class) ) {
			FactProvider sampleFactProvider = (FactProvider) this.context.getBean("sampleFactProvider", FactProvider.class);
			return sampleFactProvider;
		} else {
			throw new UnsupportedOperationException("fact type not spported");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FactSource<?> getFactSource(Class<?> factType) {
		Preconditions.checkState(context!=null, "context is not intialized");
		if ( factType.equals(SampleFact.class) ) {
			SampleFactSource alfEventSource = (SampleFactSource) this.context.getBean("sampleFactSource", SampleFactSource.class);
			return alfEventSource;
		} else {
			throw new UnsupportedOperationException("fact type not spported");
		}
	}
}
