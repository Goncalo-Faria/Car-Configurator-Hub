public class Utilizador {

	private TipoUtilizador tipoUtilizador;
	private int id;
	private String nome;
	private String password;

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

	/**
	 * 
	 * @param nome
	 * @param password
	 * @param tipo
	 */
	public Utilizador(int nome, String password, TipoUtilizador tipo) {
		throw new UnsupportedOperationException();
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