package CCH.controller.admin;

import CCH.CarConfiguratorHubApplication;
import CCH.business.CCH;
import CCH.business.Componente;
import CCH.business.Pacote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;


public class AdicionarComponentesPacoteController {
    @FXML
    public TableView table;

    @FXML
    public Button back;

    private CCH cch = CarConfiguratorHubApplication.getCch();

    private static Pacote pacote;

    public static void setPacote(Pacote newPacote) {
        pacote = newPacote;
    }

    @FXML
    public void initialize() {
        ObservableList<TableColumn> observableList = table.getColumns();

        observableList.get(0).setCellValueFactory(
                new PropertyValueFactory<Componente, String>("fullName")
        );

        observableList.get(1).setCellValueFactory(
                new PropertyValueFactory<Componente, Double>("stockAvailable")
        );

        observableList.get(2).setCellValueFactory(
                new PropertyValueFactory<Componente, Double>("preco")
        );

        //addDeleteButtonToTableColumn(observableList.get(3));

        table.setItems(getComponentes());
    }

    private ObservableList<Componente> getComponentes() {
        ObservableList<Componente> componentes = FXCollections.observableArrayList();
       // componentes.addAll(cch.consultarComponentesNoPacote(pacote.getId()));

        return componentes;
    }





    @FXML
    public void back() {
        back.getScene().getWindow().hide();
    }

}
