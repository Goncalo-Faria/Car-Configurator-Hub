package CCH.services;

import CCH.business.TipoUtilizador;
import CCH.dataaccess.TipoUtilizadorDAO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TipoUtilizadorServiceImpl implements TipoUtilizadorService {
    private TipoUtilizadorDAO tipoUtilizadorDAO = new TipoUtilizadorDAO();

    public Collection<TipoUtilizador> getAll() {
        return tipoUtilizadorDAO.values();
    }
}
