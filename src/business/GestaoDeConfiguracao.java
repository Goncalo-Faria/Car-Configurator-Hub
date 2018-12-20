package business;

import java.util.HashMap;
import java.util.Map;

public class GestaoDeConfiguracao {
	private Map<Integer,Configuracao> configuracoes;

	public GestaoDeConfiguracao() {
		this.configuracoes = new HashMap<>();
	}

	public GestaoDeConfiguracao(Map<Integer, Configuracao> configuracoes) {
		this.configuracoes = configuracoes;
	}

	public Configuracao criarConfiguracao() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param configuracaoId
	 */
	public void removerConfiguracao(int configuracaoId) {
		throw new UnsupportedOperationException();
	}

}