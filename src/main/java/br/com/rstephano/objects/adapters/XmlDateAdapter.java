package br.com.rstephano.objects.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDateAdapter extends XmlAdapter<String, Date> {
	
	private String PATTERN = "yyyy-MM-dd HH:mm:ss.sssZ";
	private static Logger logger = Logger.getLogger(XmlDateAdapter.class.getName());
	private final SimpleDateFormat dateFormat;

	public XmlDateAdapter() {
		dateFormat = new SimpleDateFormat(PATTERN);
	}

	public String marshal(Date date) throws Exception {
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			logger.log(Level.WARNING, String.format("Failed to format date %s", date.toString()), e);
			return null;
		}
	}

	public Date unmarshal(String dateString) throws Exception {
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			logger.log(Level.WARNING, String.format("Failed to parse string %s", dateString), e);
			return null;
		}
	}

}
