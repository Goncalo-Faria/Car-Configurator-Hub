package CCH.dataaccess;

import CCH.business.ClasseComponente;
import CCH.business.Componente;
import CCH.business.Pacote;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class PacoteDAO extends GenericDAOClass<Integer> {

    private ComponenteDAO componenteDAO = new ComponenteDAO();
    private ClasseComponenteDAO classeComponenteDAO = new ClasseComponenteDAO();

    public PacoteDAO () {
        super("Pacote", new Pacote(0,0.0));
    }

    public Pacote get(Object key) {
        return (Pacote)super.get(key);
    }

    public Pacote put(Integer key, Pacote value){
        return (Pacote)super.put(key,value);
    }

    public Pacote remove(Object key){
        return (Pacote)super.remove(key);
    }

    public Map<Integer, Componente> getComponentes(Integer pacoteId) {
        try {
            Map<Integer, Componente> componentes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Pacote_has_Componente WHERE Pacote_id=" + pacoteId;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Componente componente = this.componenteDAO.get(rs.getInt(2));
                componentes.put(componente.getId(), componente);
            }

            return componentes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public void updateDesconto(Pacote pacote) {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("UPDATE Pacote SET desconto = " +
                    pacote.getDesconto() +
                    " WHERE id = " +
                    pacote.getId() +
                    ";");

        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Pacote removeAllComponentes(Object key) {
        try {
            Pacote al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Pacote_has_Componente WHERE Pacote_id = " + key + ";";
            stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void removeComponente(Object key_pacote, Object key_componente) {
        try {
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Pacote_has_Componente WHERE Pacote_id = " + key_pacote + " and " + "Componente_id = " + key_componente;
            stm.executeUpdate(sql);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void adicionaComponente(Object key_pacote, Object key_componente) {
        try {
            Statement stm = conn.createStatement();
            String sql = "INSERT Pacote_has_Componente VALUES (" + key_pacote + ", " + key_componente + ");";
            stm.executeUpdate(sql);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<Integer> getAllIdsComponentesNoPacote(Object key) {
        try {
            Collection<Integer> col = new HashSet<>();
            Statement stm = conn.createStatement();

            ResultSet rs_componenteId = stm.executeQuery("SELECT Componente_id FROM Pacote_has_Componente where Pacote_id = " + key);

            while (rs_componenteId.next()) {
                Object componente_id = rs_componenteId.getInt(1);
                String sql = "SELECT id FROM Componente WHERE id=" + componente_id;
                stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);

                if (rs.next()) {
                    col.add(rs.getInt(1));
                }
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }

    public Collection<Componente> getAllComponentesNoPacote(Object key) {
        try {
            HashSet<Componente> col = new HashSet<>();

            Statement stm = conn.createStatement();

            ResultSet rs_componenteId = stm.executeQuery("SELECT Componente_id FROM Pacote_has_Componente where Pacote_id = " + key);

            while (rs_componenteId.next()) {
                int componente_id = rs_componenteId.getInt(1);
                Componente al = this.componenteDAO.get(componente_id);

                if (al != null )
                    col.add(al);
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }


}
