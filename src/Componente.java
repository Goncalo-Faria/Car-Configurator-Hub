import java.util.List;

public class Componente {
	private ClasseComponente classeComponente;
	private List<Integer> requeridos;
	private List<Integer> incompativeis;
	private int id;
	private int stock;
	private double preco;
	private String nome;

	private static int nextId = 1;

	public Componente(ClasseComponente classeComponente, List<Integer> requeridos,
					  List<Integer> incompativeis, int stock,
					  double preco, String nome) {
		this.classeComponente = classeComponente;
		this.requeridos = requeridos;
		this.incompativeis = incompativeis;
		this.id = Componente.getNextId();
		this.stock = stock;
		this.preco = preco;
		this.nome = nome;
	}

	public static int getNextId() {
		return nextId++;
	}

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