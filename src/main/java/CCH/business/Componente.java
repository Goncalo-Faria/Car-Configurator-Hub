package CCH.business;

import CCH.dataaccess.ClasseComponenteDAO;
import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.RemoteClass;

import java.util.List;
import java.util.Map;

public class Componente implements RemoteClass<Integer> {

	private ClasseComponente classeComponente;
	private int id;
	private int stock;
	private double preco;
	private String nome;

	private final ComponenteDAO componenteDAO = new ComponenteDAO();
	private final ClasseComponenteDAO classeComponenteDAO = new ClasseComponenteDAO();

	public Componente(int id, int stock, double preco, String nome, ClasseComponente classeComponente) {
		this.id = id;
		this.stock = stock;
		this.preco = preco;
		this.nome = nome;
		this.classeComponente = classeComponente;
	}

	public Componente(List<String> rs){
	    this.id = Integer.valueOf(rs.get(0));
	    this.stock = Integer.valueOf(rs.get(1));
	    this.preco = Double.valueOf(rs.get(2));
	    this.nome = rs.get(3);
    }

	public int getId() {
		return this.id;
	}

	@Override
	public List<String> toRow() {
		// não é suposto adicionar componentes.
		throw new NullPointerException(" não é suposto criar componentes");
	}

	public Componente fromRow(List<String> rs){
		return new Componente(rs);
	}

	public Integer key() {
		return this.id;
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
		return this.classeComponente;
	}

	public void setClasseComponente(ClasseComponente classeComponente) {
		this.classeComponente = classeComponente;
	}


	public Map<Integer, Componente> getRequeridos() {
		return this.componenteDAO.getComponentesRequeridos(id);
	}

	public Map<Integer, Componente> getIncompativeis() {
		return this.componenteDAO.getComponentesIncompativeis(id);
	}


	public String getFullName() {
		return this.classeComponente.getNome() + " " + this.nome;
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
