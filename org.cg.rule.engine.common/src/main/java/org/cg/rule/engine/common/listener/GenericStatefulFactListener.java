package org.cg.rule.engine.common.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.session.StatefulKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;


/**
 * A generic fact listener with StatelessKieSession setup
 * @author WZ
 *
 * @param <Fact>
 */
public class GenericStatefulFactListener<Fact> implements GenericFactListener<Fact> {

	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(GenericStatefulFactListener.class);

	@Autowired
	@Qualifier("statefulKieSession")
	private StatefulKieSession statefulKieSession;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertToKieSession(final Fact fact) {
		Preconditions.checkNotNull(fact, "fact cannot be null");
		FactHandle factHandler = null;
		try {
			LOG.info("[*****************************");
			factHandler = statefulKieSession.getKieSession().insert(fact);
			statefulKieSession.getKieSession().fireAllRules();
		} catch (Exception e) {
			LOG.error(Throwables.getStackTraceAsString(e));
		} finally {
			if (factHandler!=null) {
				statefulKieSession.getKieSession().delete(factHandler);
				LOG.info("*****************************]");
			}
		}
	}

	/**
	 * dispose session
	 */
	public void shutdown() {
		LOG.info("stateful ksession disposed");
		statefulKieSession.getKieSession().dispose();
	}
}
