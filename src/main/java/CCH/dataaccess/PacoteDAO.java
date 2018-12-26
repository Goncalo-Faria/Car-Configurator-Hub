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

}
