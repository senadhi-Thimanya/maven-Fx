package Controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tm.itemTm;

import java.io.IOException;
import java.sql.*;

public class ItemsFormController {
    public Button btnBack;
    public Button btnSave;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtPrice;
    public TextField txtQTY;
    public Button btnUpdate;
    public TableView tblItems;
    public TableColumn codeCol;
    public TableColumn descCol;
    public TableColumn priceCol;
    public TableColumn qtyCol;
    public TableColumn delCol;

    public void btnOnActionSave(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQTY.getText());

        String sql = "INSERT INTO Item VALUES(?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
//            Statement statement = connection.createStatement();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,code);
            pstm.setString(2,description);
            pstm.setDouble(3,unitPrice);
            pstm.setInt(4,qty);
            boolean isSaved = pstm.executeUpdate()>0;
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Successful..!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed..!").show();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadItems();
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
    }


    public void btnOnActionSearch(ActionEvent actionEvent) {
    }

    public void btnOnActionBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/HomePageForm.fxml"))));
        stage.show();
    }

    public void initialize(){
        codeCol.setCellValueFactory(new PropertyValueFactory("code"));
        descCol.setCellValueFactory(new PropertyValueFactory("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        qtyCol.setCellValueFactory(new PropertyValueFactory("qty"));
        delCol.setCellValueFactory(new PropertyValueFactory("delete"));
        loadItems();
    }

    private void loadItems() {
        String sql = "SELECT * FROM Item";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet =preparedStatement.executeQuery();
            ObservableList<itemTm> tmList = FXCollections.observableArrayList();
            while(resultSet.next()){
                Button button = new Button("Delete");
                itemTm itemTm = new itemTm(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        button
                );
                tmList.add(itemTm);
            }
            tblItems.setItems(tmList);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
