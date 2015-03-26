package org.cg.rule.engine.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.context.RuleEngineContext;
import org.cg.rule.engine.common.context.RuleEngineTestContext;
import org.cg.rule.engine.common.provider.FactProvider;
import org.cg.rule.engine.common.sample.model.SampleFact;

import com.google.common.base.Throwables;

/**
 * a testing utility for rule engine
 * 
 * @author WZ
 *
 */
public class RuleEngineTestUtility {
	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(RuleEngineTestUtility.class);

	public static final void main(String[] args) {
		if (args.length < 1) {
			throw new RuntimeException("Please specify -sample.fact.file.path=[filepath].");
		}
		boolean isEventProvided = false;
		for ( String arg :args ) {
			if ( arg.startsWith("-sample.fact=") ) {
				System.getProperties().setProperty("sample.fact.file.path", arg.split("=")[1]);
				isEventProvided = true;
			} else {
				throw new RuntimeException("unspported arg");
			}
		}

		if (!isEventProvided) {
			System.getProperties().setProperty("sample.fact.file.path", "");
		}

		RuleEngineContext ruleEngineContext = null;
		try {
			ruleEngineContext = RuleEngineTestContext.getInstance();

			if (isEventProvided) {
				LOG.info("Processing facts...");
				FactProvider sampleFactProvider = ruleEngineContext.getFactProvider(SampleFact.class);
				sampleFactProvider.request();
			}
		} catch (Exception e) {
			LOG.error(Throwables.getStackTraceAsString(e));
		} finally {
			if ( ruleEngineContext != null ) {
				ruleEngineContext.destroyContext();
			}
		}
	}
}
