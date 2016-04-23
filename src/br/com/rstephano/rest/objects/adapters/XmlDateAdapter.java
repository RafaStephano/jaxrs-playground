package br.com.rstephano.rest.objects.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDateAdapter extends XmlAdapter<String, Date> {

	private String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public String marshal(Date date) throws Exception {
		return new SimpleDateFormat(pattern).format(date);
	}

	public Date unmarshal(String dateString) throws Exception {
		return new SimpleDateFormat(pattern).parse(dateString);
	}

}
