package br.com.rstephano.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationJAXRS extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return super.getClasses();
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("jersey.config.server.provider.packages", "br.com.rstephano.rest");
		return properties;
	}

	@Override
	public Set<Object> getSingletons() {
		return super.getSingletons();
	}

}
