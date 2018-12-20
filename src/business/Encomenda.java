package business;

import java.util.Map;

public class Encomenda extends Configuracao {
	private String nomeCliente;
	private String numeroDeIdentificacaoCliente;
	private String moradaCliente;
	private String paisCliente;
	private String emailCliente;

	public Encomenda(Map<Integer, Componente> componentes, double preco, double desconto, String nomeCliente, String numeroDeIdentificacaoCliente, String moradaCliente, String paisCliente, String emailCliente) {
		super(componentes, preco, desconto);
		this.nomeCliente = nomeCliente;
		this.numeroDeIdentificacaoCliente = numeroDeIdentificacaoCliente;
		this.moradaCliente = moradaCliente;
		this.paisCliente = paisCliente;
		this.emailCliente = emailCliente;
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
	 * @param configuracao
	 * @param nomeCliente
	 * @param numeroDeIdentificacaoCliente
	 * @param moradaCliente
	 * @param paisCliente
	 * @param emailCliente
	 */
	public Encomenda(Configuracao configuracao, String nomeCliente,
					 String numeroDeIdentificacaoCliente, String moradaCliente,
					 String paisCliente, String emailCliente) {
		throw new UnsupportedOperationException();
	}

}