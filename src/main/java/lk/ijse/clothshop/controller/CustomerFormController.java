package lk.ijse.clothshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.clothshop.bo.BoFactory;
import lk.ijse.clothshop.bo.custom.customerBo;
import lk.ijse.clothshop.dto.tm.Customer;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.dto.tm.tm.CustomerTM;
import lk.ijse.clothshop.dto.tm.tm.EmployeeTM;
import lk.ijse.clothshop.entity.customer;
import lk.ijse.clothshop.model.CustomerModel;
import lk.ijse.clothshop.model.EmployeetableModel;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/clothshop";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public JFXTextField customeridtxt;
    public JFXTextField customernametxt;
    public JFXTextField contacttxt;
    public JFXTextField addresstxt;
    public JFXButton btnadd;
    public JFXButton btndelet;
    public JFXButton btnupdate;
    public JFXTextField txtSearch;
    public Button searchbtn;

    public TableView tableview;
    public TableColumn customeridcolumn;
    public TableColumn customernamecolumn;
    public TableColumn contactcolumn;
    public TableColumn useridcolumn;
    public JFXComboBox useridcombotxt;

    customerBo customerBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.CUSTOMER);

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String cusid = customeridtxt.getText();
        String custname = customernametxt.getText();
        String contact = contacttxt.getText();
        String userid = String.valueOf(useridcombotxt.getValue());

            if(customerBo.update(new Customer(custname,contact,cusid,userid))){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully !").show();

                setDefault();
                useridcombotxt.setValue(null);

            }

    }

    public void OnActionSearch(ActionEvent actionEvent) {
    }

    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String cusid = customeridtxt.getText();
        String custname = customernametxt.getText();
        String contact = contacttxt.getText();
        String userid = String.valueOf(useridcombotxt.getValue());

            if(customerBo.save(new Customer(custname,contact,cusid,userid))){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer add successfully !").show();
                setDefault();
                useridcombotxt.setValue(null);

                getAll();setCellValueFactory();

            }


    }

    public void OnActionDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String serch = txtSearch.getText();

            if(customerBo.delete(serch)){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully !");

                setDefault();

                useridcombotxt.setValue(null);
            }

            getAll();setCellValueFactory();


    }

    public void searchbtnonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = txtSearch.getText();

            customer cus = customerBo.search(search);
            if(cus!=null){
                setDefault();
                txtSearch.setText("");
                customeridtxt.setText(cus.getCustId());
                customernametxt.setText(cus.getCustName());
                contacttxt.setText(cus.getContact());

            }

    }

    public void txtseazrchKeyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        List<String> items = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, props)){
            String sql = "SELECT * FROM user";

            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                String id = resultSet.getString(1);
                items.add(id);
            }

            useridcombotxt.getItems().addAll(items);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        getAll();setCellValueFactory();

    }

    private void setCellValueFactory() {
        customeridcolumn.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        customernamecolumn.setCellValueFactory(new PropertyValueFactory<>("cutomername"));
        contactcolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        useridcolumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }

    private void getAll() {
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<customer> places = customerBo.getAll();

            for (customer place : places) {
                obList.add(new CustomerTM(
                        place.getCustId(),
                        place.getCustName(),
                        place.getContact(),
                        place.getUserId()
                ));
            }
            tableview.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setDefault(){
        customeridtxt.setText("");
        customernametxt.setText("");
        contacttxt.setText("");
    }

}
