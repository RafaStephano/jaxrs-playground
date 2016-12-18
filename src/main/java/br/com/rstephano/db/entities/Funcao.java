package br.com.rstephano.db.entities;

public enum Funcao {
	ADMIN("administrador"), USUARIO("usuario");
	private String funcao;

	private Funcao(String funcao) {
		this.funcao = funcao;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
}
