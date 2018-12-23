package CCH.dataaccess;

import CCH.business.*;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.sql.*;

public class UtilizadorDAO implements Map<Integer, Utilizador> {

    public Connection conn;

    private TipoUtilizadorDAO tipoUtilizadorDAO = new TipoUtilizadorDAO();

    public UtilizadorDAO () {
        conn = CCHConnection.getConnection();
    }

    public void clear () {
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Utilizador");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM Utilizador WHERE id='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }

    public Set<Map.Entry<Integer, Utilizador>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,Utilizador>> entrySet() not implemented!");
    }

    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    public Utilizador get(Object key) {
        try {
            Utilizador al = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM Utilizador WHERE id='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {

                TipoUtilizador tipoUtilizador =tipoUtilizadorDAO.get(rs.getInt(4));
                al = new Utilizador(rs.getInt(1),rs.getString(2),rs.getString(3), tipoUtilizador);
            };
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
            ResultSet rs = stm.executeQuery("SELECT id FROM Utilizador");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    public Utilizador put(Integer key, Utilizador value) {
        try {
            Utilizador u = null;
            Statement stm = conn.createStatement();

            stm.executeUpdate("DELETE FROM Utilizador WHERE numero='"+key+"'");
            String sql = "INSERT INTO Utilizador VALUES (";

            sql += "'" + key + "','" + value.getNome() + "','" + value.getPassword() + "','" + value.getTipoUtilizador().getId() +"'";

            sql += ");";

            int i  = stm.executeUpdate(sql);
            return get(key);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends Integer,? extends Utilizador> t) {
        throw new NullPointerException("Not implemented!");
    }

    public Utilizador remove(Object key) {
        try {
            Utilizador al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '"+key+"' FROM Utilizador";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM Utilizador");
            for (;rs.next();i++);
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<Utilizador> values() {
        try {
            Collection<Utilizador> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Utilizador");
            for (;rs.next();) {
                TipoUtilizador tipoUtilizador = tipoUtilizadorDAO.get(rs.getInt(4));
                Utilizador al = new Utilizador(rs.getInt(1),rs.getString(2),rs.getString(3), tipoUtilizador);
                col.add(al);
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

}

