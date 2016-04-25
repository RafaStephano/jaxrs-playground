package br.com.rstephano.rest.objects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationErrorHeader {
	private String className;
	@XmlElementWrapper
	@XmlElementRef
	private List<ValidationErrorDetail> errors = new ArrayList<ValidationErrorDetail>();

	public ValidationErrorHeader() {
		super();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ValidationErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationErrorDetail> errors) {
		this.errors = errors;
	}
}
