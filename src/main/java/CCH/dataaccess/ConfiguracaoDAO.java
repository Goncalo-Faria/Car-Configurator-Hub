package CCH.dataaccess;

import CCH.business.Componente;
import CCH.business.Configuracao;
import CCH.business.Pacote;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ConfiguracaoDAO implements Map<Integer, Configuracao> {

    public Connection conn;

    private PacoteDAO pacoteDAO = new PacoteDAO();
    private ComponenteDAO componenteDAO = new ComponenteDAO();

    public ConfiguracaoDAO () {
        conn = CCHConnection.getConnection();
    }

    public int getNextId() {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM Configuracao ORDER BY id DESC LIMIT 1;";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }

            return 0;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM Configuracao WHERE ID = " + key;
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Configuracao get(Object key) {
        try {
            Configuracao al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Configuracao WHERE id=" + key;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                al = new Configuracao(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
            }

            return al;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Configuracao put(Integer key, Configuracao value) {
        if (containsKey(key)) {
            return update(key, value);
        }
        try {
            Statement stm = conn.createStatement();

            stm.executeUpdate("DELETE FROM Configuracao WHERE id='"+key+"'");

            String sql = "INSERT INTO Configuracao VALUES ('" + value.getId() +
                    "','" + value.getPreco() + "','" + value.getDesconto() + "');";

            int i  = stm.executeUpdate(sql);

            return get(key);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Configuracao update(Integer key, Configuracao value) {
        try {
            Statement stm = conn.createStatement();

            String sql = "UPDATE Configuracao SET id = " +
                    key +
                    ", preco = " +
                    value.getPreco() +
                    ", desconto = " +
                    value.getDesconto() +
                    " WHERE id = " +
                    key +
                    ";";

            int i = stm.executeUpdate(sql);

            return get(key);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
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

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM Configuracao");

            while (rs.next()) {
                i++;
            }

            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<Configuracao> values() {
        try {
            Collection<Configuracao> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Configuracao");

            while (rs.next()) {
                Configuracao al = new Configuracao(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
                col.add(al);
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Map<Integer, Configuracao> getAll() {
        Map<Integer, Configuracao> hashmap = new HashMap<>();
        Collection<Configuracao> collection = values();

        collection.forEach(u -> hashmap.put(u.getId(), u));

        return hashmap;
    }

    public Map<Integer, Pacote> getPacotes(Integer configuracaoId) {
        try {
            Map<Integer, Pacote> pacotes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Configuracao_has_Pacote WHERE Configuracao_id=" + configuracaoId;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Pacote pacote = pacoteDAO.get(rs.getInt(2));
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

    public int hashCode() {
        return this.conn.hashCode();
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("Not implemented!");
    }

    public Set<Entry<Integer, Configuracao>> entrySet() {
        throw new NullPointerException("Not implemented!");    }

    public boolean equals(Object o) {
        throw new NullPointerException("Not implemented!");    }

    public void putAll(Map<? extends Integer,? extends Configuracao> t) {
        throw new NullPointerException("Not implemented!");
    }

    public void clear () {
        throw new NullPointerException("Not implemented!");
    }

    public boolean isEmpty() {
        throw new NullPointerException("Not implemented!");
    }

    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    public void updatePreco(Object key, double descontoAtualizado) {
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

    public void removeAllPacoteNasConfiguracoes(Object key) {
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
