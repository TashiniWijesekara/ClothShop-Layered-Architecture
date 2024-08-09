package lk.ijse.clothshop.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.clothshop.bo.custom.UserBo;
import lk.ijse.clothshop.bo.custom.supplierBo;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.dto.tm.User;
import lk.ijse.clothshop.dto.tm.tm.EmployeeTM;
import lk.ijse.clothshop.dto.tm.tm.UserTM;
import lk.ijse.clothshop.entity.user;
import lk.ijse.clothshop.model.EmployeetableModel;
import lk.ijse.clothshop.model.UserModel;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormController implements Initializable {

    public JFXButton addbtn;
    public JFXButton updatebtn;
    public JFXButton deletebtn;
    public JFXTextField idtxt;
    public JFXTextField usernametxt;
    public JFXTextField conumbertxt;
    public JFXTextField txtSearch;
    public Button searchbtn;

    public TableView tableview;
    public TableColumn idcolumn;
    public TableColumn usernamecolumn;
    public TableColumn contactnumbercolumn;

    UserBo userBo  = BoFactory.getBoFactory().getBo(BoFactory.BoType.USER);

    public void addbtnonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = idtxt.getText();
        String name = usernametxt.getText();
        String number = conumbertxt.getText();

        if(isint(id)&isname(name)&isint(number)) {
                if (userBo.save(new User(id, name, number))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User add successfull !").show();

                    setDefault();
                }

        }else {
            new Alert(Alert.AlertType.WARNING, "Please check & enter valid value !").show();
        }

    }

    public void deletebtnonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String id = idtxt.getText();

            if (userBo.delete(id)){
                new Alert(Alert.AlertType.CONFIRMATION, "User delete successfull !").show();
            }
            setDefault();

    }

    public void updatebtnonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = idtxt.getText();
        String name = usernametxt.getText();
        String conu = conumbertxt.getText();

            if(userBo.update(new User(id, name, conu))){
                new Alert(Alert.AlertType.CONFIRMATION, "User update successfull !").show();
            }

            setDefault();


    }

    public void searchbtnonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = txtSearch.getText();

            user user = userBo.search(search);
            if(user!=null){
                idtxt.setText(user.getUserId());
                usernametxt.setText(user.getUserName());
                conumbertxt.setText(user.getContact());

                txtSearch.setText("");

            }

    }

    public void txtseazrchKeyReleased(KeyEvent keyEvent) {

    }

    private void setCellValueFactory() {
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        contactnumbercolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    private void getAll() {
        try {
            ObservableList<UserTM> obList = FXCollections.observableArrayList();
            List<user> places = userBo.getAll();

            for (user place : places) {
                obList.add(new UserTM(
                        place.getUserId(),
                        place.getUserName(),
                        place.getContact()
                ));
            }
            tableview.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        getAll();setCellValueFactory();
    }

    public static boolean isname(String name) {
        String regex = "^[a-z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean isint(String intnum) {
        String regex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(intnum);
        return matcher.matches();
    }

    private void setDefault(){
        idtxt.setText("");
        usernametxt.setText("");
        conumbertxt.setText("");

        txtSearch.setText("");
    }

}
