import java.util.List;

public class OperacaoFabril {
	private Encomenda encomendas;

	public OperacaoFabril() {
	}

	public OperacaoFabril(Encomenda encomendas) {
		this.encomendas = encomendas;
	}

	public List<Componente> consultarComponentes() {
		throw new UnsupportedOperationException();
	}

	public Encomenda consultarProximaEncomenda() {
		throw new UnsupportedOperationException();
	}

	public Encomenda adiconarEncomenda() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param componenteId
	 */
	public void atualizarStock(int componenteId) {
		throw new UnsupportedOperationException();
	}

}