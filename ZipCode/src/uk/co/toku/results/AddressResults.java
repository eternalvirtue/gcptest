package uk.co.toku.results;

import java.util.ArrayList;

public class AddressResults {
	private ArrayList<ZipcodeExtend> results = new ArrayList<ZipcodeExtend>();

	public AddressResults() {
		super();
	}

	public void add(ZipcodeExtend bean) {
		results.add(bean);
	}

	public ArrayList<ZipcodeExtend> getResults() {
		return results;
	}

	public void setResults(ArrayList<ZipcodeExtend> results) {
		this.results = results;
	}
}
