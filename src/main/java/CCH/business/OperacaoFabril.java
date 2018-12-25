package CCH.business;

import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.EncomendaDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class OperacaoFabril {

	private EncomendaDAO encomendaDAO;
	private ComponenteDAO componenteDAO;

	public OperacaoFabril() {
		this.encomendaDAO = new EncomendaDAO();
		this.componenteDAO = new ComponenteDAO();
	}

	public List<Componente> consultarComponentes() {
		return new ArrayList<>(componenteDAO.values());
	}

	public Encomenda consultarProximaEncomenda() {
		TreeMap<Integer, Encomenda> sorted = new TreeMap<>(encomendaDAO.getAll());
		return sorted.firstEntry().getValue();
	}

	/**
	 *
	 * @param id
	 */
	public void removerEncomenda(Integer id) {
		encomendaDAO.remove(id);
	}

	public void atualizarStock(int componenteId, int nrComponentes) {
		// TODO - implement OperacaoFabril.atualizarStock
		throw new UnsupportedOperationException();
	}
}