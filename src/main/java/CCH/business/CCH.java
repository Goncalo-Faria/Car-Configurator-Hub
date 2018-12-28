package CCH.business;

import CCH.dataaccess.ComponenteDAO;
import CCH.dataaccess.PacoteDAO;
import CCH.dataaccess.UtilizadorDAO;
import CCH.exception.NoOptimalConfigurationException;
import CCH.exception.ComponenteIncompativelNoPacoteException;
import CCH.exception.ComponenteJaExisteNoPacoteException;
import CCH.exception.WrongCredentialsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CCH {
	private GestaoDeConfiguracao gestaoDeConfiguracao;
	private OperacaoFabril operacaoFabril;
	private UtilizadorDAO utilizadorDAO;
	private PacoteDAO pacoteDAO;
	private ComponenteDAO componenteDAO;

	public CCH() {
		this.operacaoFabril = new OperacaoFabril();
		this.gestaoDeConfiguracao = new GestaoDeConfiguracao();
		this.utilizadorDAO = new UtilizadorDAO();
		this.pacoteDAO = new PacoteDAO();
		this.componenteDAO = new ComponenteDAO();
	}

	public GestaoDeConfiguracao getGestaoDeConfiguracao() {
		return gestaoDeConfiguracao;
	}

	public void setGestaoDeConfiguracao(GestaoDeConfiguracao gestaoDeConfiguracao) {
		this.gestaoDeConfiguracao = gestaoDeConfiguracao;
	}

	public OperacaoFabril getOperacaoFabril() {
		return operacaoFabril;
	}

	public void setOperacaoFabril(OperacaoFabril operacaoFabril) {
		this.operacaoFabril = operacaoFabril;
	}

	public UtilizadorDAO getUtilizadorDAO() {
		return utilizadorDAO;
	}

	public void setUtilizadorDAO(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	public PacoteDAO getPacoteDAO() {
		return pacoteDAO;
	}

	public void setPacoteDAO(PacoteDAO pacoteDAO) {
		this.pacoteDAO = pacoteDAO;
	}

	public ComponenteDAO getComponenteDAO() {
		return componenteDAO;
	}

	public void setComponenteDAO(ComponenteDAO componenteDAO) {
		this.componenteDAO = componenteDAO;
	}

	/**
	 *
	 * @param id
	 * @param password
	 */
	public Utilizador iniciarSessao(int id, String password) throws WrongCredentialsException {
		Utilizador utilizador = utilizadorDAO.get(id);
		if (utilizador == null)
			throw new WrongCredentialsException();

		boolean loggedIn = utilizador.validarCredenciais(id, password);
		if (!loggedIn)
			throw new WrongCredentialsException();

		return utilizador;
	}

	public Pacote criarPacote() {
		int id = pacoteDAO.getNextId();
		Pacote pacote = new Pacote(id, 0);
		pacote = pacoteDAO.put(id, pacote);
		return pacote;
	}

	/**
	 *
	 * @param pacoteId
	 */
	public void removerPacote(int pacoteId) {
		gestaoDeConfiguracao.removePacote(pacoteId, pacoteDAO.getDescontoPacote(pacoteId));

		pacoteDAO.removeAllComponentes(pacoteId);
		pacoteDAO.remove(pacoteId);
	}

	public Utilizador criarUtilizador() {
		Utilizador utilizador = new Utilizador("empty", "empty");
		utilizador = utilizadorDAO.put(utilizador.getId(), utilizador);
		return utilizador;
	}

	/**
	 *
	 * @param utilizadorId
	 */
	public void removerUtilizador(int utilizadorId) {
		this.utilizadorDAO.remove(utilizadorId);
	}

	public List<Utilizador> consultarFuncionarios() {
		return this.utilizadorDAO.values().stream().
                    map(l -> (Utilizador)l).collect(Collectors.toList());
	}

	public List<Pacote> consultarPacotes() {
		return this.pacoteDAO.values().stream().
                    map(l -> (Pacote) l).collect(Collectors.toList());
	}

	public List<Componente> consultarComponentes() {
		return this.componenteDAO.values().stream().
                    map(l -> (Componente)l).collect(Collectors.toList());
	}

	public Configuracao ConfiguracaoOtima(Configuracao configuracao, double valor) throws NoOptimalConfigurationException {
		Collection<Pacote> pacs = this.consultarPacotes();
		Collection<Componente> comps = this.consultarComponentes();
		return gestaoDeConfiguracao.configuracaoOtima(comps,pacs,configuracao,valor);
	}


	public List<Componente> consultarComponentesNoPacote(int pacote_id) {
		return new ArrayList<>(pacoteDAO.getAllComponentesNoPacote(pacote_id));
	}

	public void removerComponenteDoPacote(Pacote pacote, int componente_id) {
		pacote.removeComponente(componente_id);
	}

	public void adicionarComponenteAoPacote(Pacote pacote, int componente_id) throws ComponenteJaExisteNoPacoteException, ComponenteIncompativelNoPacoteException {
		pacote.adicionaComponente(componente_id);
	}
}

