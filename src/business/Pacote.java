package business;

import java.util.HashMap;
import java.util.Map;

public class Pacote {
	private Map<Integer,Componente> componentes;
	private int id;
	private double desconto;

	private static int nextId = 1;

	public Pacote() {
		this.componentes = new HashMap<>();
		this.id = Pacote.getNextId();
		this.desconto = 0;
	}

	public Pacote(Map<Integer, Componente> componentes, double desconto) {
		this.componentes = componentes;
		this.id = Pacote.getNextId();
		this.desconto = desconto;
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

	public double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	/**
	 * 
	 * @param componenteId
	 */
	public Componente adicionarComponente(int componenteId) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param componenteId
	 */
	public void removerComponente(int componenteId) {
		throw new UnsupportedOperationException();
	}

}