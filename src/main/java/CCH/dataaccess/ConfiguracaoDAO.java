package CCH.dataaccess;

import CCH.business.Componente;
import CCH.business.Configuracao;
import CCH.business.Pacote;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ConfiguracaoDAO extends GenericDAOClass<Integer> {

    private PacoteDAO pacoteDAO = new PacoteDAO();
    private ComponenteDAO componenteDAO = new ComponenteDAO();

    public ConfiguracaoDAO () {
        super("Configuracao",
                new Configuracao(),
                Arrays.asList(new String[]{"id","preco","desconto"}));
    }

    public Configuracao get(Object key) {
        return (Configuracao)super.get(key);
    }

    public Map<Integer,Configuracao> getAllConfiguracao() {
        Map<Integer, RemoteClass<Integer>> a = super.getAll();
        Map<Integer, Configuracao> r = new HashMap<>();
        a.forEach((k,v) -> r.put(k, (Configuracao) v));
        return r;
    }

    public Configuracao update(Integer key, Configuracao value) {
        return (Configuracao)super.update(key,value);
    }

    public Configuracao remove(Object key) {
        try {
            Configuracao al = this.get(key);
            Statement stm = conn.createStatement();

            String sql = "DELETE FROM Configuracao_has_Componente WHERE Configuracao_id = " + key;
            int i  = stm.executeUpdate(sql);

            sql = "DELETE FROM Configuracao_has_Pacote WHERE Configuracao_id = " + key;
            i  = stm.executeUpdate(sql);

            sql = "DELETE FROM Configuracao WHERE id = " + key;
            i  = stm.executeUpdate(sql);

            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Configuracao put(Integer key, Configuracao value){
        return (Configuracao)super.put(key,value);
    }

    public Map<Integer, Pacote> getPacotes(Integer configuracaoId) {
        try {
            Map<Integer, Pacote> pacotes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Configuracao_has_Pacote WHERE Configuracao_id=" + configuracaoId;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Pacote pacote = this.pacoteDAO.get(rs.getInt(2));
                pacotes.put(pacote.getId(), pacote);
            }

            return pacotes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Pacote addPacote(Integer configuracaoId, Integer pacoteId) {
        try {
            Statement stm = conn.createStatement();

            String sql = "INSERT Configuracao_has_Pacote VALUE (" +
                    configuracaoId +
                    "," +
                    pacoteId +
                    ");";
            int i  = stm.executeUpdate(sql);
            return pacoteDAO.get(pacoteId);
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public void removePacote(Integer configuracaoId, Integer pacoteId) {
        try {
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Configuracao_has_Pacote WHERE Configuracao_id=" +
                    configuracaoId +
                    " AND Pacote_id=" +
                    pacoteId +
                    ";";

            int i = stm.executeUpdate(sql);
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Map<Integer, Componente> getComponentes(Integer configuracaoId) {
        try {
            Map<Integer, Componente> componentes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Configuracao_has_Componente WHERE Configuracao_id=" + configuracaoId;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Componente componente = componenteDAO.get(rs.getInt(2));
                componentes.put(componente.getId(), componente);
            }

            return componentes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Componente addComponente(Integer configuracaoId, Integer componenteId) {
        try {
            Statement stm = conn.createStatement();

            String sql = "INSERT Configuracao_has_Componente VALUE (" +
                    configuracaoId +
                    "," +
                    componenteId +
                    ");";
            int i  = stm.executeUpdate(sql);
            return componenteDAO.get(componenteId);
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Componente removeComponente(Integer configuracaoId, Integer componenteId) {
        try {
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Configuracao_has_Componente WHERE Configuracao_id=" +
                    configuracaoId +
                    " AND Componente_id=" +
                    componenteId +
                    ";";

            int i = stm.executeUpdate(sql);
            return componenteDAO.get(componenteId);
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public void updateDesconto(Object key, double descontoAtualizado) {
        try {
            Statement stm = conn.createStatement();

            String sql = "UPDATE Configuracao SET desconto = " +
                    descontoAtualizado +
                    " WHERE id = " +
                    key +
                    ";";

            stm.executeUpdate(sql);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void removePacoteNasConfiguracoes(Object key) {
        try {
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Configuracao_has_Pacote WHERE Pacote_id = " + key + ";";
            stm.executeUpdate(sql);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public double getDescontoConfiguracao(Object key) {
        try {
            double al = 0.0;
            Statement stm = conn.createStatement();
            String sql = "SELECT desconto FROM Configuracao WHERE id=" + key;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                al = rs.getInt(1);
            }

            return al;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public List<Integer> getAllIdsConfiguracoesComOPacote(Object pacoteId) {
        try {
            List<Integer> idsConfiguracoes = new ArrayList<>();;

            Statement stm = conn.createStatement();
            String sql = "SELECT Configuracao_id FROM Configuracao_has_Pacote WHERE Pacote_id = " + pacoteId + ";";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                idsConfiguracoes.add(rs.getInt(1));
            }

            return idsConfiguracoes;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
