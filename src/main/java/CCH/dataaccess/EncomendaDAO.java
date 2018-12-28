package CCH.dataaccess;

import CCH.business.Componente;
import CCH.business.Configuracao;
import CCH.business.Encomenda;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class EncomendaDAO extends GenericDAOClass<Integer> {


    public EncomendaDAO () {
        super("Encomenda",
                new Encomenda(),
                Arrays.asList(new String[]{"id","nomeCliente",
                        "numeroDeIdentificaoCliente","moradaCliente","paisCliente",
                        "emailCliente","emailCliente","preco"}));
    }

    public Encomenda get(Object key) {
        return (Encomenda)super.get(key);
    }

    public Map<Integer,Encomenda> getAllEncomenda() {
        Map<Integer, RemoteClass<Integer>> a = super.getAll();
        Map<Integer, Encomenda> r = new HashMap<>();
        a.forEach((k,v) -> r.put(k, (Encomenda) v));
        return r;
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
