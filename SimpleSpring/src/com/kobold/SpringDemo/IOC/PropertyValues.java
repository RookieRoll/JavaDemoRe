package com.kobold.SpringDemo.IOC;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyValues {
	private final List<PropertyValue> propertyValues = new ArrayList<>();

	public PropertyValues() {
	}

	public void addPropertyValue(PropertyValue propertyValue) {
		propertyValues.add(propertyValue);
	}

	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}
}
