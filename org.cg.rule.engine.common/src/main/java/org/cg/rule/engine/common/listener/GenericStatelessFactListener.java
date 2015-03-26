package org.cg.rule.engine.common.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.session.StatelessKieSession;
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
public class GenericStatelessFactListener<Fact> implements GenericFactListener<Fact> {

	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(GenericStatelessFactListener.class);
	
	@Autowired
	@Qualifier("statelessKieSession")
	private StatelessKieSession statelessKieSession;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertToKieSession(final Fact fact) {
		Preconditions.checkNotNull(fact, "fact cannot be null");
		try {
			statelessKieSession.getKieSession().execute(fact);
		} catch (Exception e) {
			LOG.error(Throwables.getStackTraceAsString(e));
		}
	}
}
