package CCH.controller.gestaoDeConfiguracao;

import CCH.business.CCH;
import CCH.business.Componente;
import CCH.business.Configuracao;
import CCH.business.Pacote;
import CCH.exception.NoOptimalConfigurationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import CCH.CarConfiguratorHubApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class ConfiguracaoOtimaController {
    @FXML
    public TableView table;

    @FXML
    public TextField valor;

    @FXML
    public Button back;

    private static Configuracao configuracao;

    public static void setConfiguracao(Configuracao newConfiguracao) {
        configuracao = newConfiguracao;
    }

    private CCH cch = CarConfiguratorHubApplication.getCch();

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

        table.setItems(null);
    }

    @FXML
    private void loadConfiguracaoOtima() {
        ObservableList<Componente> componentes = FXCollections.observableArrayList();

        try {
            componentes.addAll(cch.ConfiguracaoOtima(configuracao,Double.parseDouble(valor.getText()))
                        .consultarComponentes().values());
        } catch (NoOptimalConfigurationException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("");
            alert.setContentText("Não existe uma configuração ótima.");
            alert.showAndWait();
        }

        table.setItems(componentes);
    }

    @FXML
    public void back() {
        back.getScene().getWindow().hide();
    }
}
