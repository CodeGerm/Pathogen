package org.cg.rule.engine.common.sample.model;

import java.io.Serializable;

public class SampleFact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3334491273164704428L;
	
	private int propertyA;
	private int propertyB;
	
	public SampleFact() {
		super();
	}

	public SampleFact(int propertyA, int propertyB) {
		super();
		this.propertyA = propertyA;
		this.propertyB = propertyB;
	}

	public int getPropertyA() {
		return propertyA;
	}
	
	public void setPropertyA(int propertyA) {
		this.propertyA = propertyA;
	}
	
	public int getPropertyB() {
		return propertyB;
	}
	
	public void setPropertyB(int propertyB) {
		this.propertyB = propertyB;
	}

	@Override
	public String toString() {
		return "SampleFact [propertyA=" + propertyA + ", propertyB="
				+ propertyB + "]";
	}
	
}
