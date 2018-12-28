package CCH.business;

import CCH.dataaccess.ClasseComponenteDAO;
import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.RemoteClass;

import java.util.List;
import java.util.*;

public class Componente implements RemoteClass<Integer> {

	private ClasseComponente classeComponente;
	private int id;
	private int stock;
	private double preco;
	private String nome;

	private ComponenteDAO componenteDAO = null;
	private ClasseComponenteDAO classeComponenteDAO = null;

	public Componente() {
	}

	public Componente(int id, int stock, double preco, String nome, ClasseComponente classeComponente) {
		this.id = id;
		this.stock = stock;
		this.preco = preco;
		this.nome = nome;
		this.classeComponente = classeComponente;
		this.componenteDAO = new ComponenteDAO();
		this.classeComponenteDAO = new ClasseComponenteDAO();
	}

	public Componente(List<String> rs){
	    this.id = Integer.valueOf(rs.get(0));
	    this.stock = Integer.valueOf(rs.get(1));
	    this.preco = Double.valueOf(rs.get(2));
	    this.nome = rs.get(3);
		this.componenteDAO = new ComponenteDAO();
		this.classeComponenteDAO = new ClasseComponenteDAO();
	    this.classeComponente = this.classeComponenteDAO.get(Integer.valueOf(rs.get(4)));
    }

	public int getId() {
		return this.id;
	}

	@Override
	public List<String> toRow() {
		List<String> l = new LinkedList<>();
		l.add(String.valueOf(this.id));
		l.add(String.valueOf(this.stock));
		l.add(String.valueOf(this.preco));
		l.add(this.nome);
		l.add(String.valueOf(this.classeComponente.key()));
		return l;
	}

	public Componente fromRow(List<String> rs){
		return new Componente(rs);
	}

	public Integer key() {
		return this.id;
	}

    public Integer key(String k) {
        return Integer.valueOf(k);
    }

	public void setId(int id) {
		this.id = id;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClasseComponente getClasseComponente() {
		return classeComponente;
	}

	public void setClasseComponente(ClasseComponente classeComponente) {
		this.classeComponente = classeComponente;
	}


	public Map<Integer, Componente> getRequeridos() {
		return componenteDAO.getComponentesRequeridos(id);
	}

	public Map<Integer, Componente> getIncompativeis() {
		return componenteDAO.getComponentesIncompativeis(id);
	}


	public String getFullName() {
		return classeComponente.getNome() + " " + nome;
	}

	public String getStockString() {
		return Integer.toString(stock);
	}

	public String getStockAvailable() {
		if (stock > 20) {
			return "Disponível";
		} else if (stock > 0) {
			return "Limitado";
		}

		return "Indisponível";
	}
}
