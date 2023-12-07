package Controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerFormController {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnOnActionSubmit(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        String sql = "INSERT INTO Customer VALUES('"+id+"','"+name+"','"+address+"',"+salary+")";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            boolean isSaved = stm.executeUpdate(sql) > 0;
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Saved Successfully!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
