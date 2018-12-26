package CCH.business;

import CCH.dataaccess.ComponenteDAO;
<<<<<<< HEAD
import CCH.dataaccess.PacoteDAO;
import CCH.exception.ComponenteIncompativelNoPacoteException;
import CCH.exception.ComponenteJaExisteNoPacoteException;

import java.util.Map;

public class Pacote {
=======
import CCH.dataaccess.RemoteClass;

import java.rmi.Remote;
import java.util.LinkedList;
import java.util.List;

public class Pacote implements RemoteClass<Integer> {

	private ComponenteDAO componentes = new ComponenteDAO();
>>>>>>>  pacote dao
	private int id;
	private double desconto;
	private PacoteDAO pacoteDAO = new PacoteDAO();


	public Pacote(int id, double desconto) {
		this.id = id;
		this.desconto = desconto;
	}

	public Pacote() {
		this.id = pacoteDAO.getNextId();
		this.desconto = 0.0;
	}

	public int getId() {
		return this.id;
	}

	public Integer key(){return this.id; }

	@Override
	public Pacote fromRow(List<String> row) {
		return new Pacote(row);
	}

	@Override
	public List<String> toRow() {
		List<String> l = new LinkedList<>();
		l.add(String.valueOf(this.id));
		l.add(String.valueOf(this.desconto));
		return l;
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

<<<<<<< HEAD
	public Map<Integer, Componente> getComponentes() {
		return pacoteDAO.getComponentes(id);
=======
	public ComponenteDAO getComponentes() {
		return componentes;
	}

	public void setComponentes(ComponenteDAO componentes) {
		this.componentes = componentes;
	}

	public Pacote(int id, double desconto) {
		this.id = id;
		this.desconto = desconto;
	}

	public Pacote(List<String> rs){
		this.id = Integer.valueOf(rs.get(0));
		this.desconto = Double.valueOf(rs.get(1));
>>>>>>>  pacote dao
	}

	/**
	 *
	 * @param componenteId
	 */
	public void adicionaComponente(int componenteId) throws ComponenteJaExisteNoPacoteException, ComponenteIncompativelNoPacoteException {
		boolean alreadyHas = pacoteDAO.getAllIdsComponentesNoPacote(this.id).contains(componenteId);
		if (alreadyHas)
			throw new ComponenteJaExisteNoPacoteException();

		for (Componente c : pacoteDAO.getAllComponentesNoPacote(this.id)) {
			if (c.getIncompativeis() != null && c.getIncompativeis().containsKey(componenteId))
				throw new ComponenteIncompativelNoPacoteException(c.getFullName());

		}

		pacoteDAO.adicionaComponente(this.id, componenteId);
	}

	/**
	 *
	 * @param componenteId
	 */
	public void removeComponente(int componenteId) {
		pacoteDAO.removeComponente(this.id, componenteId);
	}

	public String getNome() {
		return "Pacote " + id;
	}

	public String getDescontoString() {
		return Double.toString(this.desconto);
	}

	public void atualizarDesconto(Pacote pacote) {
		pacoteDAO.updateDesconto(pacote);
	}
}
