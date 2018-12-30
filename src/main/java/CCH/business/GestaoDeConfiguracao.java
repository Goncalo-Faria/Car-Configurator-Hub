package CCH.business;

import CCH.dataaccess.ConfiguracaoDAO;
import CCH.dataaccess.EncomendaDAO;
import CCH.exception.EncomendaRequerOutrosComponentes;
import CCH.exception.EncomendaTemComponentesIncompativeis;
import CCH.exception.EncomendaRequerObrigatoriosException;
import CCH.exception.NoOptimalConfigurationException;
import CCH.exception.ConfiguracaoNaoTemObrigatoriosException;
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

    /**
     * Construtor por omissão da GestaoDeConfiguracao.
     */
	public GestaoDeConfiguracao() {
		this.configuracoes = new ConfiguracaoDAO();
		this.encomendas = new EncomendaDAO();
	}

	public ConfiguracaoDAO getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(ConfiguracaoDAO configuracoes) {
		this.configuracoes = configuracoes;
	}

	public void criarConfiguracao() {
		Configuracao configuracao = new Configuracao(configuracoes.getNextId(), 0, 0);
		configuracoes.put(configuracao.getId(), configuracao);
	}

	/**
	 *
	 * @param configuracaoId
	 */
	public void removerConfiguracao(int configuracaoId) {
		configuracoes.remove(configuracaoId);
	}

	public List<Configuracao> consultarConfiguracoes() {
		return new ArrayList<>(configuracoes.values());
	}

	public void criarEncomenda(
				Configuracao configuracao,
				String nomeCliente,
				String numeroDeIdentificacaoCliente,
				String moradaCliente,
				String paisCliente,
				String emailCliente
	) throws EncomendaRequerOutrosComponentes, EncomendaTemComponentesIncompativeis, EncomendaRequerObrigatoriosException {
		Map<Integer, Componente> componentes = configuracao.verificaValidade();
		int id = encomendas.getNextId();
		Encomenda encomenda = new Encomenda(componentes, id,
											configuracao.getPreco(), nomeCliente,
											numeroDeIdentificacaoCliente, moradaCliente,
											paisCliente, emailCliente);
		encomendas.put(id, encomenda);
	}

	public Configuracao configuracaoOtima(Collection<Componente> componentes, Collection<Pacote> pacotes, Configuracao configuracao, double valor) throws NoOptimalConfigurationException, ConfiguracaoNaoTemObrigatoriosException {
		if (valor < 0) {
			throw new NoOptimalConfigurationException("Negative Value");
		}

		try {
			configuracao.verificaValidade();
		} catch (EncomendaTemComponentesIncompativeis encomendaTemComponentesIncompativeis) {
			throw new NoOptimalConfigurationException();
		} catch (EncomendaRequerOutrosComponentes encomendaRequerOutrosComponentes) {
		} catch (EncomendaRequerObrigatoriosException e) {
			throw new ConfiguracaoNaoTemObrigatoriosException();
		}

		ConfiguracaoOtima c = new ConfiguracaoOtima();
    
		Collection<Componente> componentesObrigatorios = configuracoes.getComponentes(configuracao.getId()).values();

		try {
			return c.configuracaoOtima(componentesObrigatorios,componentes,pacotes,valor);
		} catch (IloException e) {
			throw new NoOptimalConfigurationException();
		}
	}

	public void removePacote (int pacoteId, double desconto){
		List <Integer> configuracoesId = configuracoes.getAllIdsConfiguracoesComOPacote(pacoteId);

		configuracoes.removePacoteNasConfiguracoes(pacoteId);

		for(Integer configuracaoId : configuracoesId) {
			double descontoAtualizado =
					configuracoes.getDescontoConfiguracao(configuracaoId) - desconto;

			configuracoes.updateDesconto(configuracaoId, descontoAtualizado);
		}
	}
}
