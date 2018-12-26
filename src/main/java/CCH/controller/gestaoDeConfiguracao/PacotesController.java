package CCH.controller.gestaoDeConfiguracao;

import CCH.CarConfiguratorHubApplication;
import CCH.business.CCH;
import CCH.business.Configuracao;
import CCH.business.GestaoDeConfiguracao;
import CCH.business.Pacote;
import CCH.exception.PacoteJaAdicionadoException;
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

public class PacotesController {
    @FXML public TableView table;

    @FXML public Button back;

    private static Configuracao configuracao;
    public static void setConfiguracao(Configuracao newConfiguracao) {
        configuracao = newConfiguracao;
    }

    private CCH cch = CarConfiguratorHubApplication.getCch();
    private GestaoDeConfiguracao gestaoDeConfiguracao = cch.getGestaoDeConfiguracao();

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

        table.setItems(getPacotes());

        setSelection();
    }

    private ObservableList<Pacote> getPacotes() {
        ObservableList<Pacote> pacotes = FXCollections.observableArrayList();
        pacotes.addAll(cch.consultarPacotes());
        return pacotes;
    }

    private void addComponenteButtonToTableColumn(TableColumn t) {
        Callback<TableColumn<Pacote, Void>, TableCell<Pacote, Void>> cellFactory = new Callback<TableColumn<Pacote, Void>, TableCell<Pacote, Void>>() {
            @Override
            public TableCell<Pacote, Void> call(final TableColumn<Pacote, Void> param) {
                final TableCell<Pacote, Void> cell = new TableCell<Pacote, Void>() {

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

    private void loadComponentes(Pacote pacote) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/gestaoDeConfiguracao/componentes.fxml"));
            ComponentesController.setPacote(pacote);
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initOwner(back.getScene().getWindow());
            stage.setScene(scene);

            stage.showAndWait();
            initialize();
        } catch (IOException e) { }
    }

    private void setSelection() {
        table.setRowFactory(tv -> {
            TableRow<Pacote> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Pacote pacote = null;
                try {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        pacote = row.getItem();
                        configuracao.adicionarPacote(pacote.getId());
                        ((Stage) back.getScene().getWindow()).close();
                    }
                } catch (PacoteJaAdicionadoException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Pacote já adicionado");
                    alert.setContentText("Esta configuração já contém o " + pacote.getNome());

                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return row;
        });
    }

    @FXML
    public void back() {
        ((Stage) back.getScene().getWindow()).close();
    }
}
