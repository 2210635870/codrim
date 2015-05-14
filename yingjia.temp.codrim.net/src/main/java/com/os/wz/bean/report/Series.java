package com.os.wz.bean.report;

import java.util.ArrayList;
import java.util.List;

public class Series {

	private String name;
	
	private List<Element> elements = new ArrayList<Element>();
	
	public void addElement(Element e) {
		elements.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

}
