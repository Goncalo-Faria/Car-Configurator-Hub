package CCH.dataaccess;

import CCH.business.*;

import java.util.*;
import java.sql.*;

public class TipoUtilizadorDAO implements Map<Integer, TipoUtilizador> {

    public Connection conn;

    public TipoUtilizadorDAO () {
        conn = CCHConnection.getConnection();
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM TipoUtilizador WHERE ID = " + key;
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public TipoUtilizador get(Object key) {
        try {
            TipoUtilizador al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM TipoUtilizador WHERE id=" + key;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                al = new TipoUtilizador(rs.getInt(1),rs.getString(2));
            }

            return al;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public TipoUtilizador put(Integer key, TipoUtilizador value) {
        try {
            Statement stm = conn.createStatement();

            stm.executeUpdate("DELETE FROM TipoUtilizador WHERE id='"+key+"'");
            String sql = "INSERT INTO TipoUtilizador VALUES ('" +
                    value.getId() + "','" + value.getTipo() + "');";

            int i  = stm.executeUpdate(sql);

            return get(key);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public TipoUtilizador remove(Object key) {
        try {
            TipoUtilizador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE " + key + " FROM TipoUtilizador";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM TipoUtilizador");

            while (rs.next()) {
                i++;
            }

            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<TipoUtilizador> values() {
        try {
            Collection<TipoUtilizador> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM TipoUtilizador");

            while (rs.next()) {
                TipoUtilizador al = new TipoUtilizador(rs.getInt(1),rs.getString(2));
                col.add(al);
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Map<Integer, TipoUtilizador> getAll() {
        Map<Integer, TipoUtilizador> hashmap = new HashMap<>();
        Collection<TipoUtilizador> collection = values();

        collection.forEach(u -> hashmap.put(u.getId(), u));

        return hashmap;
    }

    public int hashCode() {
        return this.conn.hashCode();
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("Not implemented!");
    }

    public Set<Map.Entry<Integer, TipoUtilizador>> entrySet() {
        throw new NullPointerException("Not implemented!");    }

    public boolean equals(Object o) {
        throw new NullPointerException("Not implemented!");    }

    public void putAll(Map<? extends Integer,? extends TipoUtilizador> t) {
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
}

