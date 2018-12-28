package CCH.business;

import CCH.dataaccess.PacoteDAO;
import CCH.dataaccess.RemoteClass;
import CCH.exception.ComponenteIncompativelNoPacoteException;
import CCH.exception.ComponenteJaExisteNoPacoteException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pacote implements RemoteClass<Integer> {
	private int id;
	private double desconto;
	private PacoteDAO pacotes;

	public Pacote(int id, double desconto) {
		this.pacotes = new PacoteDAO();
		this.id = id;
		this.desconto = desconto;
	}

	public Pacote() {
	}

	public int getId() {
		return this.id;
	}

	public Integer key(){return this.id; }

    public Integer key(String k) {
        return Integer.valueOf(k);
    }

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

	public double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Map<Integer, Componente> getComponentes() {
		return pacotes.getComponentes(id);
	}


	public Pacote(List<String> rs){
		this.id = Integer.valueOf(rs.get(0));
		this.desconto = Double.valueOf(rs.get(1));
		this.pacotes = new PacoteDAO();
	}

	/**
	 *
	 * @param componenteId
	 */
	public void adicionaComponente(int componenteId) throws ComponenteJaExisteNoPacoteException, ComponenteIncompativelNoPacoteException {
		boolean alreadyHas = pacotes.getAllIdsComponentesNoPacote(this.id).contains(componenteId);
		if (alreadyHas)
			throw new ComponenteJaExisteNoPacoteException();

		for (Componente c : pacotes.getAllComponentesNoPacote(this.id)) {
			if (c.getIncompativeis() != null && c.getIncompativeis().containsKey(componenteId))
				throw new ComponenteIncompativelNoPacoteException(c.getFullName());

		}

		pacotes.adicionaComponente(this.id, componenteId);
	}

	/**
	 *
	 * @param componenteId
	 */
	public void removeComponente(int componenteId) {
		pacotes.removeComponente(this.id, componenteId);
	}

	public String getNome() {
		return "Pacote " + id;
	}

	public String getDescontoString() {
		return Double.toString(this.desconto);
	}

	public void atualizarDesconto(Pacote pacote) {
		pacotes.updateDesconto(pacote);
	}

	@Override
	public String toString() {
		return "Pacote{" +
				"id=" + id +
				", desconto=" + desconto +
				'}';
	}
}
