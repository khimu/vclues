package com.vclues.core.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
public class DataField extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field(value="field_name")
	private String fieldName;
	
	@Field(value="field_value")
	private String fieldValue;
	
	@Field(value="validation_required")
	private Boolean validationRequired;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Boolean getValidationRequired() {
		return validationRequired;
	}

	public void setValidationRequired(Boolean validationRequired) {
		this.validationRequired = validationRequired;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
