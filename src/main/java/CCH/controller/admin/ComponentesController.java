package CCH.controller.admin;

import CCH.business.CCH;
import CCH.business.Componente;
import CCH.business.GestaoDeConfiguracao;
import CCH.business.Pacote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import CCH.CarConfiguratorHubApplication;
import CCH.business.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class ComponentesController {
    @FXML
    public TableView table;

    @FXML
    public Button back;

    private static Pacote newPacote;
    private Pacote pacote;

    public static void setPacote(Pacote newPacote) {
        newPacote = newPacote;
    }

    @FXML
    public void initialize() {
        pacote = CarConfiguratorHubApplication.getCch().getPacoteDAO().get(newPacote.getId());

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

        return componentes;
    }


    @FXML
    public void back() {
        back.getScene().getWindow().hide();
    }
}
