package CCH.dataaccess;

import CCH.business.Componente;
import CCH.business.Configuracao;
import CCH.business.Encomenda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class EncomendaDAO extends GenericDAOClass<Integer> {


    public EncomendaDAO () {
        super("Encomenda",new Encomenda(0,null,
                null,null,null,null));
    }

    public Configuracao get(Object key) {
        return (Configuracao)super.get(key);
    }

    public Encomenda put(Integer key, Encomenda value){
        return (Encomenda)super.put(key,value);
    }

    public Encomenda remove(Object key){
        return (Encomenda)super.remove(key);
    }

    public Map<Integer, Componente> getComponentes(Integer encomendaId) {
        try {
            Map<Integer, Componente> componentes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Encomenda_has_Componente WHERE Encomenda_id=" + encomendaId;
            ResultSet rs = stm.executeQuery(sql);

            ComponenteDAO componenteDAO = new ComponenteDAO();
            if (rs.next()) {
                Componente componente = componenteDAO.get(rs.getInt(2));
                componentes.put(componente.getId(), componente);
            }

            return componentes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
