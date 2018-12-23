package CCH.controller;

import CCH.dataaccess.TipoUtilizadorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TipoUtilizadorController {
    @FXML
    public ComboBox<String> tipoUtilizadoresComboBox;

    private TipoUtilizadorDAO tipoUtilizadorDAO = new TipoUtilizadorDAO();

    @FXML
    public void initialize() {
        List<String> tipoUtilizadores = new ArrayList<>();
        ObservableList<String> observableList = FXCollections.observableList(tipoUtilizadores);
        observableList.addAll(
                tipoUtilizadorDAO.values()
                        .stream()
                        .map(t -> t.getTipo())
                        .collect(Collectors.toList())
        );

        tipoUtilizadoresComboBox.setItems(observableList);
    }
}
