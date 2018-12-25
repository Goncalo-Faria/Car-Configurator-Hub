package CCH.controller;

import CCH.CarConfiguratorHubApplication;
import CCH.business.Componente;
import CCH.business.Encomenda;
import CCH.business.OperacaoFabril;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class OperacaoFabrilController {
    @FXML
    public TableView table;

    @FXML
    public Label idEncomenda;

    private OperacaoFabril operacaoFabril = CarConfiguratorHubApplication.getCch().getOperacaoFabril();

    @FXML
    public void initialize() {
        Encomenda encomenda = operacaoFabril.consultarProximaEncomenda();

        if (encomenda != null) {
            idEncomenda.setText(Integer.toString(encomenda.getId()));
        } else {
            idEncomenda.setText("Nenhuma encomenda disponível");
        }

        table.setEditable(true);
        table.getSelectionModel().cellSelectionEnabledProperty().set(true);

        ObservableList<TableColumn> observableList = table.getColumns();

        observableList.get(0).setCellValueFactory(
                new PropertyValueFactory<Componente, String>("fullName")
        );

        observableList.get(1).setCellValueFactory(
                new PropertyValueFactory<Componente, Integer>("stock")
        );

        observableList.get(2).setCellValueFactory(
                new PropertyValueFactory<Componente, Double>("preco")
        );

        ObservableList<Componente> componenteList = FXCollections.observableArrayList();
        componenteList.addAll(operacaoFabril.consultarComponentes());
        table.setItems(componenteList);
    }

    @FXML
    public void concluir() {
        System.out.println("hey");
        try {
            int encomendaId = Integer.parseInt(idEncomenda.getText());
            System.out.println(encomendaId);
            operacaoFabril.removerEncomenda(encomendaId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Encomenda encomenda = operacaoFabril.consultarProximaEncomenda();

        if (encomenda != null) {
            idEncomenda.setText(Integer.toString(encomenda.getId()));
        } else {
            idEncomenda.setText("Nenhuma encomenda disponível");
        }
    }

    @FXML
    public void updateStock(CellEditEvent<Componente, String> event) {
            Componente componente = event.getTableView().getItems().get(event.getTablePosition().getRow());
            componente.setStock(Integer.parseInt(event.getNewValue()));

            operacaoFabril.atualizarStock(componente.getId(), componente.getStock());
            table.refresh();
    }
}
