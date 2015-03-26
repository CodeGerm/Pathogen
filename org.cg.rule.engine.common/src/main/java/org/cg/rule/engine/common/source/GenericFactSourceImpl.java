package org.cg.rule.engine.common.source;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.listener.GenericFactListener;

/**
 * Default implementation of generic fact source
 * @author WZ
 *
 */
public class GenericFactSourceImpl<Fact> implements FactSource<Fact> {
	
	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(GenericFactSourceImpl.class);
	
	private List<GenericFactListener<Fact>> factListeners = new ArrayList<GenericFactListener<Fact>>();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void addFactListener(GenericFactListener<Fact> listener)  {
		LOG.info(String.format("%s listener added", listener.getClass()));
		factListeners.add(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void removeFactListener(GenericFactListener<Fact> listener)   {
		LOG.info(String.format("%s listener removed", listener.getClass()));
		factListeners.remove(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void insertFact(Fact fact) {
		for ( GenericFactListener<Fact> listeners: factListeners ) {
			listeners.insertToKieSession(fact);
		}
	}
	
	public List<GenericFactListener<Fact>> getFactListeners () {
		return factListeners;
	}

	public void setFactListeners(List<GenericFactListener<Fact>> factListeners) {
		this.factListeners = factListeners;
	}
}
