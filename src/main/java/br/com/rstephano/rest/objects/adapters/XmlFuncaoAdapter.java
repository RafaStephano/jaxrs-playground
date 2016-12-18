package br.com.rstephano.rest.objects.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import br.com.rstephano.objects.Funcao;

public class XmlFuncaoAdapter extends XmlAdapter<String, Funcao> {

	public Funcao unmarshal(String v) throws Exception {
		for (Funcao f : Funcao.values()) {
			if (f.getFuncao().trim().equals(v)) {
				return f;
			}
		}
		return null;
	}

	public String marshal(Funcao v) throws Exception {
		return v.getFuncao();
	}

}
