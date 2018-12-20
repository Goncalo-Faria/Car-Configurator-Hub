import java.util.List;

public class Componente {
	private ClasseComponente classeComponente;
	private List<Integer> requeridos;
	private List<Integer> incompativeis;
	private int id;
	private int stock;
	private double preco;
	private String nome;

	public int getId() {
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

}