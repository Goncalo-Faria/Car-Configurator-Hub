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

public class PacotesController {
    @FXML
    public TableView table;

    @FXML
    public Button back;

    private CCH cch = CarConfiguratorHubApplication.getCch();

    @FXML
    public void initialize() {
        ObservableList<TableColumn> observableList = table.getColumns();

        observableList.get(0).setCellValueFactory(
                new PropertyValueFactory<Pacote, String>("nome")
        );

        addComponenteButtonToTableColumn(observableList.get(1));

        observableList.get(2).setCellValueFactory(
                new PropertyValueFactory<Pacote, Integer>("desconto")
        );

        addDeleteButtonToTableColumn(observableList.get(3));


        table.setItems(getPacotes());
    }

    private ObservableList<Pacote> getPacotes() {
        ObservableList<Pacote> pacotes = FXCollections.observableArrayList();
        pacotes.addAll(cch.consultarPacotes());
        return pacotes;
    }

    private void addComponenteButtonToTableColumn(TableColumn t) {
        Callback<TableColumn<Pacote, Void>, TableCell<Pacote, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Pacote, Void> call(final TableColumn<Pacote, Void> param) {
                final TableCell<Pacote, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Abrir");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Pacote pacote = getTableView().getItems().get(getIndex());
                            loadComponentes(pacote);
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

    private void addDeleteButtonToTableColumn(TableColumn t) {
        Callback<TableColumn<Pacote, Void>, TableCell<Pacote, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Pacote, Void> call(final TableColumn<Pacote, Void> param) {
                final TableCell<Pacote, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Apagar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Pacote pacote = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Aviso");
                            alert.setHeaderText("O " + pacote.getNome() + " será permanentemente apagado.");
                            alert.setContentText("Pretende continuar?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                cch.removerPacote(pacote.getId());
                                table.setItems(getPacotes());
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

    private void loadComponentes(Pacote pacote) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admin/componentes.fxml"));
            Parent root = fxmlLoader.load();
            ComponentesController controller = fxmlLoader.getController();
            controller.setPacote(pacote);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initOwner(back.getScene().getWindow());
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException e) { }
    }

    public void loadFuncionarios() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/funcionarios.fxml"));

        Stage stage = new Stage();
        stage.initOwner(back.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        stage.showAndWait();
    }

    @FXML
    public void back() {
        back.getScene().getWindow().hide();
    }
}
