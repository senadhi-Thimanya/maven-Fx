package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageFormController {
    public Button btnCustomers;

    public void btnOnActionCustomers(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCustomers.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"))));
        stage.show();
    }

    public void btnOnActionItems(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCustomers.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemsForm.fxml"))));
        stage.show();
    }

    public void btnOnActionOrders(ActionEvent actionEvent) throws IOException {
    }
}

