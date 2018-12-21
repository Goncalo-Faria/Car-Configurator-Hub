package business;

import java.util.List;
import java.util.Map;

public class OperacaoFabril {
	private Map<Integer,Encomenda> encomendas;

	public OperacaoFabril() {
	}

	public OperacaoFabril(Map<Integer,Encomenda> encomendas) {
		this.encomendas = encomendas;
	}

	public List<Componente> consultarComponentes() {
		throw new UnsupportedOperationException();
	}

	public Encomenda consultarProximaEncomenda() {
		throw new UnsupportedOperationException();
	}

	public void adiconarEncomenda(Encomenda encomenda) {
		throw new UnsupportedOperationException();
	}

}