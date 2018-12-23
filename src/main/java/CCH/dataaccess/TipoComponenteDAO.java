package CCH.dataaccess;

import CCH.business.TipoComponente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class TipoComponenteDAO implements Map<Integer, TipoComponente> {

    public Connection conn;

    public TipoComponenteDAO () {
        conn = CCHConnection.getConnection();
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM TipoComponente WHERE ID = " + key;
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public TipoComponente get(Object key) {
        try {
            TipoComponente al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM TipoComponente WHERE id=" + key;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                al = new TipoComponente(rs.getInt(1),rs.getString(2));
            }

            return al;
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public TipoComponente put(Integer key, TipoComponente value) {
        try {
            Statement stm = conn.createStatement();

            stm.executeUpdate("DELETE FROM TipoComponente WHERE id='"+key+"'");
            String sql = "INSERT INTO TipoComponente VALUES ('" +
                    value.getId() + "','" + value.getTipo() + "');";

            int i  = stm.executeUpdate(sql);

            return get(key);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public TipoComponente remove(Object key) {
        try {
            TipoComponente al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE " + key + " FROM TipoComponente";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM TipoComponente");

            while (rs.next()) {
                i++;
            }

            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<TipoComponente> values() {
        try {
            Collection<TipoComponente> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM TipoComponente");

            while (rs.next()) {
                TipoComponente al = new TipoComponente(rs.getInt(1),rs.getString(2));
                col.add(al);
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Map<Integer, TipoComponente> getAll() {
        Map<Integer, TipoComponente> hashmap = new HashMap<>();
        Collection<TipoComponente> collection = values();

        collection.forEach(u -> hashmap.put(u.getId(), u));

        return hashmap;
    }

    public int hashCode() {
        return this.conn.hashCode();
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("Not implemented!");
    }

    public Set<Map.Entry<Integer, TipoComponente>> entrySet() {
        throw new NullPointerException("Not implemented!");    }

    public boolean equals(Object o) {
        throw new NullPointerException("Not implemented!");    }

    public void putAll(Map<? extends Integer,? extends TipoComponente> t) {
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
