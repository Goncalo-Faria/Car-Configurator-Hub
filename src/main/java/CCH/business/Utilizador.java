package CCH.business;

public class Utilizador {
	private TipoUtilizador tipoUtilizador;
	private int id;
	private String nome;
	private String password;

	private static int nextId = 1;

	public Utilizador(String nome, String password, TipoUtilizador tipoUtilizador) {
		this.id = getNextId();
		this.nome = nome;
		this.password = password;
		this.tipoUtilizador = tipoUtilizador;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUtilizador getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(TipoUtilizador tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}

	/**
	 *
	 * @param id
	 * @param nome
	 * @param password
	 * @param tipoUtilizador
	 */
	public Utilizador(int id, String nome, String password, TipoUtilizador tipoUtilizador) {
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.tipoUtilizador = tipoUtilizador;
	}

	/**
	 * 
	 * @param id
	 * @param password
	 */
	public boolean validarCredenciais(int id, String password) {
		throw new UnsupportedOperationException();
	}

}