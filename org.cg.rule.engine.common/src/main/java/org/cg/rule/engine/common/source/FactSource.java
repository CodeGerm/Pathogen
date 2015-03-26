package org.cg.rule.engine.common.source;

import org.cg.rule.engine.common.listener.GenericFactListener;

/**
 * a generic fact source interface
 * @author WZ
 *
 */
public interface FactSource<Fact> {


	/**
	 * add a listener to the source 
	 * @param listener
	 */
	public void addFactListener(GenericFactListener<Fact> listener);
	

	/**
	 * remove a listener to the source 
	 * @param listener
	 */
	public void removeFactListener(GenericFactListener<Fact> listener);


	/**
	 * insert fact to the source
	 * @param fact
	 */
	public void insertFact(Fact fact);
}
