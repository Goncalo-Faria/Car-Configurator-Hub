package CCH.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.ResultSet;

public abstract class GenericDAOClass<K> implements Map<K, RemoteClass<K>> {

    public final Connection conn = CCHConnection.getConnection();
    private final String tablename;
    private final RemoteClass<K> token;


    public GenericDAOClass (String tablename, RemoteClass<K> token) {
        this.tablename = tablename;
        this.token = token;
    }

    public int getNextId() {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM " + this.tablename + " ORDER BY id DESC LIMIT 1;";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }

            return 0;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM " + this.tablename + " WHERE ID = " + key + " ;";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public RemoteClass<K> get(Object key) {
        try {
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM " + this.tablename + " WHERE id = " + key + " ;";
            ResultSet rs = stm.executeQuery(sql);

            List<String> row = new LinkedList<>();
            int col = rs.getMetaData().getColumnCount();
            for( int i = 1; i<= col; i++)
                row.add(rs.getString(i));

            if (rs.next()) {
                return token.fromRow(row);
            }

            return null;
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public RemoteClass<K> put(K key, RemoteClass<K> value) {
        try {
            Statement stm = conn.createStatement();

            RemoteClass<K> result = this.get(key);

            stm.executeUpdate("DELETE FROM " + this.tablename + " WHERE id = "+key+" ;");

            StringBuilder sql = new StringBuilder("INSERT INTO " + this.tablename + " VALUES (");

            List<String> l = value.toRow();

            for( int i = 0; i < (l.size()-1); i++ ) {
                sql.append(l.get(i) + ",");
            }
            sql.append(l.get(l.size()-1)+");");


            stm.executeUpdate(sql.toString());

            return result;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public RemoteClass<K> remove(Object key) {
        try {
            RemoteClass<K> al = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM " + this.tablename + " WHERE id = " + key + " ;";
            int i  = stm.executeUpdate(sql);
            return al;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public int size() {
        try {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM " + this.tablename + " ;");

            return Integer.valueOf(rs.getString(1));

        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public Collection<RemoteClass<K>> values() {
        try {
            Collection<RemoteClass<K>> set = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM " + this.tablename + " ;");

            List<String> row = new LinkedList<>();
            int col = rs.getMetaData().getColumnCount();
            for( int i = 1; i<= col; i++)
                row.add(rs.getString(i));

            while (rs.next()) {
                set.add( this.token.fromRow(row) );
            }

            return set;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public Map<K, RemoteClass<K>> getAll() {
        Map<K, RemoteClass<K>> hashmap = new HashMap<>();
        Collection<RemoteClass<K>> collection = values();

        collection.forEach(u -> hashmap.put(u.key(), u));

        return hashmap;
    }

    public boolean containsValue(Object value) {
        throw new NullPointerException("Not implemented yet!");
    }

    public Set<Entry<K, RemoteClass<K>>> entrySet() {
        throw new NullPointerException("Not implemented yet!");    }

    public boolean equals(Object o) {
        throw new NullPointerException("Not implemented yet!");    }

    public void putAll(Map<? extends K,? extends RemoteClass<K>> t) {
        throw new NullPointerException("Not implemented yet!");
    }

    public void clear () {
        throw new NullPointerException("Not implemented yet!");
    }

    public boolean isEmpty() {
        throw new NullPointerException("Not implemented yet!");
    }

    public Set<K> keySet() {
        throw new NullPointerException("Not implemented yet!");
    }
}
