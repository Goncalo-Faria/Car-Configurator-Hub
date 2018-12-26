package CCH.controller.gestaoDeConfiguracao;

import CCH.business.Pacote;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ComponentesController {
    @FXML
    public Button back;

    private static Pacote pacote;

    public static void setPacote(Pacote newPacote) {
        pacote = newPacote;
    }

    @FXML
    public void back() {
        ((Stage) back.getScene().getWindow()).close();
    }
}
