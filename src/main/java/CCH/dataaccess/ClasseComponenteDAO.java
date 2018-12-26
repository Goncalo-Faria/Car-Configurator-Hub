package CCH.dataaccess;

import CCH.business.ClasseComponente;
import CCH.business.TipoComponente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ClasseComponenteDAO extends GenericDAOClass<Integer> {

    public ClasseComponenteDAO () {
        super("ClasseComponente",
                new ClasseComponente(0, true, "lol",
                        TipoComponente.withValue(0)));
    }

    public ClasseComponente get(Object key) {
        return (ClasseComponente)super.get(key);
    }

    public ClasseComponente put(Integer key, ClasseComponente value){
        return (ClasseComponente)super.put(key,value);
    }

    public ClasseComponente remove(Object key){
        return (ClasseComponente)super.remove(key);
    }

}
