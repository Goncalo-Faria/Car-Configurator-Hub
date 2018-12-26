package CCH.business;

import CCH.dataaccess.RemoteClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Encomenda implements RemoteClass<Integer> {

	private Map<Integer, Componente> componentes;
	private int id;
	private double preco;
	private String nomeCliente;
	private String numeroDeIdentificacaoCliente;
	private String moradaCliente;
	private String paisCliente;
	private String emailCliente;

	public Map<Integer, Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(Map<Integer, Componente> componentes) {
		this.componentes = componentes;
	}

	public int getId() {
		return id;
	}

	public Integer key(){ return id;}

	@Override
	public List<String> toRow() {
		List<String> l = new LinkedList<>();
		l.add(String.valueOf(this.id));
		l.add(this.nomeCliente);
		l.add(this.numeroDeIdentificacaoCliente);
		l.add(this.moradaCliente);
		l.add(this.paisCliente);
		l.add(this.emailCliente);
		return l;
	}

	@Override
	public Encomenda fromRow(List<String> row) {
		return new Encomenda(row);
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

    public Integer key(String k) {
        return Integer.valueOf(k);
    }

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroDeIdentificacaoCliente() {
		return this.numeroDeIdentificacaoCliente;
	}

	public void setNumeroDeIdentificacaoCliente(String numeroDeIdentificacaoCliente) {
		this.numeroDeIdentificacaoCliente = numeroDeIdentificacaoCliente;
	}

	public String getMoradaCliente() {
		return this.moradaCliente;
	}

	public void setMoradaCliente(String moradaCliente) {
		this.moradaCliente = moradaCliente;
	}

	public String getPaisCliente() {
		return this.paisCliente;
	}

	public void setPaisCliente(String paisCliente) {
		this.paisCliente = paisCliente;
	}

	public String getEmailCliente() {
		return this.emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	/**
	 *
	 * @param id
	 * @param nomeCliente
	 * @param numeroDeIdentificacaoCliente
	 * @param moradaCliente
	 * @param paisCliente
	 * @param emailCliente
	 */
	public Encomenda(int id, String nomeCliente, String numeroDeIdentificacaoCliente, String moradaCliente, String paisCliente, String emailCliente) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.numeroDeIdentificacaoCliente = numeroDeIdentificacaoCliente;
		this.moradaCliente = moradaCliente;
		this.paisCliente = paisCliente;
		this.emailCliente = emailCliente;
	}

	public Encomenda(List<String> row) {
		this.id = Integer.valueOf(row.get(0));
		this.nomeCliente = row.get(1);
		this.numeroDeIdentificacaoCliente = row.get(2);
		this.moradaCliente = row.get(3);
		this.paisCliente = row.get(4);
		this.emailCliente = row.get(5);
	}

	public Encomenda(Map<Integer, Componente> componentes, int id, double preco, String nomeCliente, String numeroDeIdentificacaoCliente, String moradaCliente, String paisCliente, String emailCliente) {
		this.componentes = componentes;
		this.id = id;
		this.preco = preco;
		this.nomeCliente = nomeCliente;
		this.numeroDeIdentificacaoCliente = numeroDeIdentificacaoCliente;
		this.moradaCliente = moradaCliente;
		this.paisCliente = paisCliente;
		this.emailCliente = emailCliente;
	}
}
