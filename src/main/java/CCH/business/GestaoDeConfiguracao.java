package CCH.business;

import CCH.dataaccess.ConfiguracaoDAO;
import CCH.dataaccess.EncomendaDAO;
import CCH.exception.*;
import ilog.concert.IloException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Classe que representa as informações essenciais para que
 * uma configuração possa progredir.
 *
 * @version 20181229
 */

public class GestaoDeConfiguracao {
	private ConfiguracaoDAO configuracoes;
	private EncomendaDAO encomendas;
	private Configuracao configuracaoatual;
    /**
     * Construtor por omissão da GestaoDeConfiguracao.
     */
	public GestaoDeConfiguracao() {
		this.configuracoes = new ConfiguracaoDAO();
		this.encomendas = new EncomendaDAO();
	}

	/**
	 * Devolve o DAO que contém as informações de todas as configurações
	 * presentes no sistema.
	 *
	 * @return ConfiguracaoDAO
	 */
	public ConfiguracaoDAO getConfiguracoes() {
		return configuracoes;
	}

	/**
	 * Atualiza o DAO que contém as informações de todas as configurações
	 * 	 * presentes no sistema.
	 *
	 * @param configuracoes Novo DAO das configurações
	 */
	public void setConfiguracoes(ConfiguracaoDAO configuracoes) {
		this.configuracoes = configuracoes;
	}

	/**
	 * Método que cria uma nova configuração, com as informações default, no
	 * sistema.
	 */
	public void criarConfiguracao() {
		Configuracao configuracao = new Configuracao(configuracoes.getNextId(), 0, 0);
		configuracoes.put(configuracao.getId(), configuracao);
	}

	/**
	 * Método que remove a configuração com o id passado como parâmetro
	 * do sistema.
	 *
	 * @param configuracaoId Id da configuração que se pretende remover
	 */
	public void removerConfiguracao(int configuracaoId) {
		configuracoes.remove(configuracaoId);
	}

	/**
	 * Método que devolve todas as configurações presentes no sistema.
	 *
	 * @return List<Configuracao> Lista com todas as configurações no sistema
	 */
	public List<Configuracao> consultarConfiguracoes() {
		return new ArrayList<>(configuracoes.values());
	}

	/**
	 * Método que cria uma nova encomenda no sistema.
	 *
	 * @param nomeCliente Nome do cliente a que a encomenda corresponde
	 * @param numeroDeIdentificacaoCliente Número de Identificação do cliente
	 * @param moradaCliente Morada do cliente
	 * @param paisCliente País do cliente
	 * @param emailCliente E-mail do cliente
	 * @throws EncomendaTemComponentesIncompativeis Se a configuração tem componentes
	 * incompatíveis
	 * @throws EncomendaRequerOutrosComponentes Se existem componentes na configuração
	 * que requerem outros componentes que não estão presentes na mesma
	 * @throws EncomendaRequerObrigatoriosException Se a configuração não tem todos
	 * os componentes obrigatórios
	 */
	public void criarEncomenda(
            String nomeCliente,
            String numeroDeIdentificacaoCliente,
            String moradaCliente,
            String paisCliente,
            String emailCliente
    ) throws EncomendaRequerOutrosComponentes, EncomendaTemComponentesIncompativeis, EncomendaRequerObrigatoriosException {
		Map<Integer, Componente> componentes = configuracaoatual.verificaValidade();
		int id = encomendas.getNextId();
		Encomenda encomenda = new Encomenda(componentes, id,
											configuracaoatual.getPreco(), nomeCliente,
											numeroDeIdentificacaoCliente, moradaCliente,
											paisCliente, emailCliente);
		encomendas.put(id, encomenda);
	}

	/**
	 * Método que gera uma configuração ótima, ou seja, uma configuração que tenta
	 * maximizar a utilização do dinheiro previsto.
	 *
	 * @param componentes Todos os componentes presentes no sistema
	 * @param pacotes Todos os pacotes presentes no sistema
	 * @param valor Valor máximo disponível
	 * @return Configuracao ótima gerada
	 * @throws NoOptimalConfigurationException Caso a configuração que se pretende
	 * otimizar contenha componentes incompatíveis
	 * @throws ConfiguracaoNaoTemObrigatoriosException Caso a configuração que se pretende
	 * otimizar não contenha todos os componentes obrigatórios
	 */
	public Configuracao configuracaoOtima(Collection<Componente> componentes, Collection<Pacote> pacotes, double valor) throws NoOptimalConfigurationException, ConfiguracaoNaoTemObrigatoriosException {
		if (valor < 0) {
			throw new NoOptimalConfigurationException("Negative Value");
		}

		try {
			configuracaoatual.verificaValidade();
		} catch (EncomendaTemComponentesIncompativeis encomendaTemComponentesIncompativeis) {
			throw new NoOptimalConfigurationException();
		} catch (EncomendaRequerOutrosComponentes encomendaRequerOutrosComponentes) {
		} catch (EncomendaRequerObrigatoriosException e) {
			throw new ConfiguracaoNaoTemObrigatoriosException();
		}

		ConfiguracaoOtima c = new ConfiguracaoOtima();
    
		Collection<Componente> componentesObrigatorios = configuracoes.getComponentes(configuracaoatual.getId()).values();

		try {
			return c.configuracaoOtima(componentesObrigatorios,componentes,pacotes,valor);
		} catch (IloException e) {
			throw new NoOptimalConfigurationException();
		}
	}

	/**
	 * Método que remove um determinado pacote da configuração.
	 *
	 * @param pacoteId Id do pacote que se pretende remover da configuração
	 * @param desconto Desconto associado ao pacote
	 */
	public void removePacote (int pacoteId, double desconto){
		List <Integer> configuracoesId = configuracoes.getAllIdsConfiguracoesComOPacote(pacoteId);

		configuracoes.removePacoteNasConfiguracoes(pacoteId);

		for(Integer configuracaoId : configuracoesId) {
			double descontoAtualizado =
					configuracoes.getDescontoConfiguracao(configuracaoId) - desconto;

			configuracoes.updateDesconto(configuracaoId, descontoAtualizado);
		}
	}

	public void removerComponente(int id) {
		configuracaoatual.removerComponente(id);
	}

	public void removerPacoteConfig(int id) {
		configuracaoatual.removerPacote(id);
	}

	public List<Componente> componentesRequeremMeNaConfig(int id) {
		return configuracaoatual.componentesRequeremMeNaConfig(id);
	}

	public Configuracao getConfigAtual() {
		return configuracaoatual;
	}

	public Pacote adicionarPacote(int id, Pacote p) throws PacoteJaAdicionadoException {
		return configuracaoatual.adicionarPacote(id,p);
	}

	public List<Componente> componentesRequeridosQueNaoEstaoConfig(Map<Integer, Componente> comps) {
		return configuracaoatual.componentesRequeridosQueNaoEstaoConfig(comps);
	}

	public List<Componente> componentesIncompativeisNaConfig(Map<Integer, Componente> comps) {
		return configuracaoatual.componentesIncompativeisNaConfig(comps);
	}

	public void adicionarComponenten(int id) throws ComponenteJaAdicionadoException {
		configuracaoatual.adicionarComponente(id);
	}

	public boolean checkforPacotesInConfiguration() {
		return configuracaoatual.checkforPacotesInConfiguration();
	}

	public void loadConfigAtual(int id) {
		configuracaoatual = configuracoes.get(id);
	}
}
