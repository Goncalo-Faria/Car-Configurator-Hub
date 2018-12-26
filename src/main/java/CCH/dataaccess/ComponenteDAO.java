package CCH.dataaccess;

import CCH.business.ClasseComponente;
import CCH.business.Componente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class ComponenteDAO extends GenericDAOClass<Integer> {

    public ComponenteDAO () {
        super("Componente",
                new Componente(0,0,0.0,"lol",null),
                Arrays.asList(new String[]{"id","stock","preco","nome","ClasseComponente_id"}));
    }

    public Componente get(Object key) {
        return (Componente)super.get(key);
    }

    public Componente put(Integer key, Componente value){
        return (Componente)super.put(key,value);
    }

    public Componente remove(Object key){
        return (Componente)super.remove(key);
    }


    public Map<Integer, Componente> getComponentesIncompativeis(Integer componenteId) {
        try {
            Map<Integer, Componente> componentes = new HashMap<>();
            Statement stm = conn.createStatement();

            String sql = "SELECT * FROM Componente_incompativel_Componente WHERE " +
                    "Componente_id=" + componenteId + " OR " +
                    "Componente_id1=" + componenteId + ";";

            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Componente componente;

                if (rs.getInt(1) == componenteId)
                    componente = get(rs.getInt(2));
                else
                    componente = get(rs.getInt(1));

                componentes.put(componente.getId(), componente);
            }

            return componentes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }


    public Map<Integer, Componente> getComponentesRequeridos(Integer componenteId) {
        try {
            Map<Integer, Componente> componentes = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Componente_requer_Componente WHERE Componente_id=" + componenteId;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Componente componente = get(rs.getInt(2));
                componentes.put(componente.getId(), componente);
            }

            return componentes;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Componente updateStock(Componente componente) {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("UPDATE Componente SET stock = " +
                    componente.getStock() +
                    " WHERE id = " +
                    componente.getId() +
                    ";");

            return get(componente.getId());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
