package org.cg.rule.engine.common.provider;

/**
 * This is the fact provider interface
 * @author WZ
 *
 * @param <FactEntity>
 */
public interface FactProvider {
	
	/**
	 * request fact from the DAO and insert to the corresponding fact source
	 * @param entityClass
	 * @return
	 */
	void request ();
	
	/**
	 * 
	 */
	void discard ();	
}
