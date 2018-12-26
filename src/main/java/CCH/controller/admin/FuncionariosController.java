package CCH.controller.admin;

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


public class FuncionariosController {
    @FXML
    public TableView table;

    @FXML
    public Button back;

    private CCH cch = CarConfiguratorHubApplication.getCch();

    @FXML
    public void initialize() {
        ObservableList<TableColumn> observableList = table.getColumns();

        observableList.get(0).setCellValueFactory(
                new PropertyValueFactory<Utilizador, String>("nome")
        );

        observableList.get(1).setCellValueFactory(
                new PropertyValueFactory<Utilizador, String>("password")
        );

        observableList.get(2).setCellValueFactory(
                new PropertyValueFactory<Utilizador, String>("nomeUtilizador")
        );

        observableList.get(3).setCellValueFactory(
                new PropertyValueFactory<Utilizador, String>("nomeTipoUtilizador")
        );

        addDeleteButtonToTableColumn(observableList.get(4));

        table.setItems(getUtilizadores());
    }

    private ObservableList<Utilizador> getUtilizadores() {
        ObservableList<Utilizador> utilizadores = FXCollections.observableArrayList();
        utilizadores.addAll(cch.consultarFuncionarios());
        return utilizadores;
    }

    private void addDeleteButtonToTableColumn(TableColumn t) {
        Callback<TableColumn<Utilizador, Void>, TableCell<Utilizador, Void>> cellFactory = new Callback<TableColumn<Utilizador, Void>, TableCell<Utilizador, Void>>() {
            @Override
            public TableCell<Utilizador, Void> call(final TableColumn<Utilizador, Void> param) {
                final TableCell<Utilizador, Void> cell = new TableCell<Utilizador, Void>() {

                    private final Button btn = new Button("Apagar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Utilizador utilizador = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Aviso");
                            alert.setContentText("Pretende continuar?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                table.setItems(getUtilizadores());
                                table.refresh();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        t.setCellFactory(cellFactory);
    }

    @FXML
    public void add() {

    }

    @FXML
    public void back() {
        back.getScene().getWindow().hide();
    }

}
