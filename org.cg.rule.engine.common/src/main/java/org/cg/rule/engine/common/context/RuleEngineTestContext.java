package org.cg.rule.engine.common.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.provider.FactProvider;
import org.cg.rule.engine.common.sample.model.SampleFact;
import org.cg.rule.engine.common.source.SampleFactSource;
import org.cg.rule.engine.common.source.FactSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

/**
 * A testing context that loads applicationContext_test.xml
 * @author WZ
 *
 */
public class RuleEngineTestContext implements RuleEngineContext {
	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(RuleEngineTestContext.class);
	
	private static volatile RuleEngineTestContext instance = null;
	
	private volatile ApplicationContext context;
	
	private RuleEngineTestContext() {
		LOG.info("Starting a Rule Engine");
		try {
			context = new ClassPathXmlApplicationContext("applicationContext_test.xml");
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
	 * get a RuleEngineTestContext instance
	 * @return
	 */
	public static RuleEngineTestContext getInstance() {
		if ( instance!=null ){
			return instance;
		}
		synchronized (RuleEngineTestContext.class) {
			if ( instance!=null ){
				return instance;
			}
			instance = new RuleEngineTestContext();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroyContext() {
		if ( context != null ) {
			try {
				((ClassPathXmlApplicationContext) context).close();
			} catch (Exception e) {
				LOG.error(Throwables.getStackTraceAsString(e));
			}
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
