package CCH.dataaccess;

import CCH.business.TipoUtilizador;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TipoUtilizadorDAO implements Map<Integer, TipoUtilizador> {

    private Connection conn;

    public TipoUtilizadorDAO () {
        conn = CCHConnection.getConnection();
    }

    public void clear () {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM TipoUtilizador");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM TipoUtilizador WHERE id='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }

    public Set<Map.Entry<Integer, TipoUtilizador>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,TipoUtilizador>> entrySet() not implemented!");
    }

    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    public TipoUtilizador get(Object key) {
        try {
            TipoUtilizador al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM TipoUtilizador WHERE id='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                al = new TipoUtilizador(rs.getInt(1),rs.getString(2));
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int hashCode() {
        return this.conn.hashCode();
    }

    public boolean isEmpty() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM TipoUtilizador");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    public TipoUtilizador put(Integer key, TipoUtilizador value) {
        try {
            TipoUtilizador u = null;
            Statement stm = conn.createStatement();

            stm.executeUpdate("DELETE FROM TipoUtilizador WHERE id='" + key + "'");
            String sql = "INSERT INTO TipoUtilizador VALUES ('" + key +"','" + value.getTipo() + "');";


            int i = stm.executeUpdate(sql);
            return new TipoUtilizador(value.getId(), value.getTipo());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends Integer,? extends TipoUtilizador> t) {
        throw new NullPointerException("Not implemented!");
    }

    public TipoUtilizador remove(Object key) {
        try {
            TipoUtilizador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '"+key+"' FROM TipoUtilizador";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM TipoUtilizador");
            for (;rs.next();i++);
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<TipoUtilizador> values() {
        try {
            Collection<TipoUtilizador> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM TipoUtilizador");
            for (;rs.next();) {
                col.add(new TipoUtilizador(rs.getInt(1),rs.getString(2)));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
}
