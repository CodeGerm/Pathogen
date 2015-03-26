package org.cg.rule.engine.common.listener;

/**
 * A generic fact listener interface
 * @author WZ
 *
 * @param <Fact>
 */
public interface GenericFactListener<Fact> extends java.util.EventListener {
	
	/**
	 * insert fact to KieSession
	 * @param fact
	 */
	public void insertToKieSession (Fact fact);
}
