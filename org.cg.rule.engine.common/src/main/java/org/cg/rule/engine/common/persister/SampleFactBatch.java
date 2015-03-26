package org.cg.rule.engine.common.persister;

import java.util.ArrayList;
import java.util.List;

import org.cg.rule.engine.common.sample.model.SampleFact;

/**
 * 
 * just a wrapper to serialize JSON
 * @author WZ
 *
 */
public class SampleFactBatch {
	
	private List<SampleFact> sampleFacts;

	public SampleFactBatch() {
		sampleFacts = new ArrayList<SampleFact>();
	}
	
	public void add(SampleFact s) {
		this.sampleFacts.add(s);
	}

	public List<SampleFact> getAll() {
		return sampleFacts;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for ( SampleFact e : sampleFacts ) {
			sb.append(e.toString()).append("\n");
		}
		return sb.toString();
	}
}
