package CCH.business;

import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.PacoteDAO;

import java.util.List;

public class Pacote {

	private List<Componente> componentes;
	private int id;
	private double desconto;

	private PacoteDAO pacoteDAO = new PacoteDAO();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public ComponenteDAO getComponentes() {
		return pacoteDAO.ge;
	}

	public void setComponentes(ComponenteDAO componentes) {
		this.componentes = componentes;
	}

	public Pacote(int id, double desconto) {
		this.id = id;
		this.desconto = desconto;
		this.componentes = new ComponenteDAO();
	}

	/**
	 *
	 * @param componenteId
	 */
	public Componente adicionarComponente(int componenteId) {
		// TODO - implement Pacote.adicionarComponente
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param componenteId
	 */
	public void removerComponente(int componenteId) {
		// TODO - implement Pacote.removerComponente
		throw new UnsupportedOperationException();
	}

	public Pacote() {
		// TODO - implement Pacote.Pacote
		throw new UnsupportedOperationException();
	}

	public String getNome() {
		return "Pacote " + id;
	}

}