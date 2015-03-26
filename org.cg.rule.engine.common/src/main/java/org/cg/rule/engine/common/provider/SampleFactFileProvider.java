package org.cg.rule.engine.common.provider;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.persister.SampleFactFileDao;
import org.cg.rule.engine.common.sample.model.SampleFact;
import org.cg.rule.engine.common.source.SampleFactSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A example of provider implemented for SampleFact
 * @author WZ
 *
 */
public class SampleFactFileProvider implements FactProvider {

	/**
	 * logger
	 */
	private static final Log LOG = LogFactory.getLog(SampleFactFileProvider.class);

	@Autowired
	private SampleFactFileDao sampleFileDao;

	@Autowired
	private SampleFactSource sampleFactSource;

	@Override
	public void request() {
		List<SampleFact> sampleFacts = sampleFileDao.loadAll();

		if ( sampleFacts!= null && !sampleFacts.isEmpty() ) {			
			LOG.info(String.format("processing %s facts", sampleFacts.size()));
			for ( SampleFact sampleFact : sampleFacts ) {
				sampleFactSource.insertFact(sampleFact);
			}
		}
	}

	@Override
	public void discard() {
		// TODO Auto-generated method stub
	}

}
