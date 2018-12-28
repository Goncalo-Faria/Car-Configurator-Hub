package CCH.business;

import CCH.dataaccess.RemoteClass;
import CCH.dataaccess.UtilizadorDAO;
import CCH.exception.TipoUtilizadorInexistenteException;

import java.util.LinkedList;
import java.util.List;

public class Utilizador implements RemoteClass<Integer> {

	private int id;
	private String nome;
	private String password;
	private TipoUtilizador tipoUtilizador;

	private UtilizadorDAO utilizadorDAO;

	public Utilizador() {
	}

	/**
	 *
	 * @param nome
	 * @param password
	 */
	public Utilizador(String nome, String password) {
		this.utilizadorDAO = new UtilizadorDAO();
		this.id = utilizadorDAO.getNextId();
		this.nome = nome;
		this.password = password;
		this.tipoUtilizador = TipoUtilizador.STAND; //default
	}

	public Utilizador(int id, String nome, String password, TipoUtilizador tipoUtilizador) {
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.tipoUtilizador = tipoUtilizador;
		this.utilizadorDAO = new UtilizadorDAO();
	}

	public Utilizador(List<String> rs) {
		this.id = Integer.valueOf(rs.get(0));
		this.nome = rs.get(1);
		this.password = rs.get(2);
		this.tipoUtilizador = TipoUtilizador.withValue(Integer.valueOf(rs.get(3)));
		this.utilizadorDAO = new UtilizadorDAO();
	}

	public int getId() {
		return this.id;
	}

    public Integer key(String k) {
        return Integer.valueOf(k);
    }


	public Integer key(){ return this.id; }
	

	@Override
	public List<String> toRow() {
		List<String> l = new LinkedList<>();
		l.add(String.valueOf(this.id));
		l.add(this.nome);
		l.add(this.password);
		l.add(String.valueOf(this.tipoUtilizador.getValue()));
		return l;
	}

	@Override
	public Utilizador fromRow(List<String> row) {
		return new Utilizador(row);
	}

	public TipoUtilizador getTipoUtilizador() {
		return tipoUtilizador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTipoUtilizadorValue(int value) {
		TipoUtilizador tipoUtilizador = TipoUtilizador.values()[value];

		this.tipoUtilizador = tipoUtilizador;
	}

	/**
	 *
	 * @param id
	 * @param password
	 */
	public boolean validarCredenciais(int id, String password) {
		return this.id == id && this.password.equals(password);
	}

	public String getNomeUtilizador() {
		return "Utilizador " + id;
	}

	public String getNomeTipoUtilizador() {
		if (tipoUtilizador == TipoUtilizador.ADMIN)
			return "Admin";
		else if (tipoUtilizador == TipoUtilizador.FABRICA)
			return "Fabrica";
		else
			return "Stand";
	}

	public int parseNomeTipoToValue(String nomeTipo) throws TipoUtilizadorInexistenteException {
		if (nomeTipo.equals("Admin"))
			return 0;
		else if (nomeTipo.equals("Fabrica"))
			return 1;
		else if (nomeTipo.equals("Stand"))
			return 2;
		else
			throw new TipoUtilizadorInexistenteException();
	}

	public void atualizarUser(Utilizador utilizador) {
		utilizadorDAO.updateUser(utilizador);
	}

	public void atualizarPassword(Utilizador utilizador) {
		utilizadorDAO.updatePassword(utilizador);
	}

	public void atualizarTipo(Utilizador utilizador) {
		utilizadorDAO.updateTipo(utilizador);
	}

}
