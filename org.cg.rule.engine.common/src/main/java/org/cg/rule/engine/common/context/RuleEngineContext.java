package org.cg.rule.engine.common.context;

import org.cg.rule.engine.common.provider.FactProvider;
import org.cg.rule.engine.common.source.FactSource;
import org.springframework.context.ApplicationContext;

/**
 * This is the Rule Engine interface
 * @author WZ
 *
 */
public interface RuleEngineContext {
	
	/**
	 * get context
	 */
	ApplicationContext getContext();
	
	/**
	 * destroy/clean up a spring context
	 */
	void destroyContext();
	
	/**
	 * get a fact provider of a fact type
	 * @return
	 */
	FactProvider getFactProvider(Class<?> factType);

	/**
	 * get a fact source of a fact type
	 * @return
	 */
	FactSource<?> getFactSource(Class<?> factType);
}
