package org.cg.rule.engine.common.persister;

import java.io.IOException;
import java.util.List;

import org.cg.rule.engine.common.sample.model.SampleFact;

/**
 * generate testing data
 * @author WZ
 *
 */
public class SampleFactFileGenerator {


	public static void main(String args[]) throws IOException {
		SampleFactFileDao sampleFileDao = new SampleFactFileDao("src/main/resources/data/sampleFacts.json");
		SampleFactBatch batch = new SampleFactBatch();
		List<SampleFact> sampleFacts = sampleFileDao.loadAll();
		
		for (SampleFact sampleFact :  sampleFacts) {
			batch.add(sampleFact);
		}
		
		batch.add(generateSampleFact());
		sampleFileDao.save(batch);
		System.out.println("done!");
	}
	
	public static SampleFact generateSampleFact() {
		return new SampleFact(1,2);
	}
}
