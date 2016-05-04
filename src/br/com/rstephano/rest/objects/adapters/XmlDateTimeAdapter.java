package br.com.rstephano.rest.objects.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class XmlDateTimeAdapter extends XmlAdapter<String, DateTime> {

	private static DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

	public DateTime unmarshal(String v) throws Exception {
		return new DateTime(v);
	}

	public String marshal(DateTime v) throws Exception {
		return fmt.print(v);
	}

}
