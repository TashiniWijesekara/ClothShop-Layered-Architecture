package lk.ijse.clothshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.clothshop.bo.BoFactory;
import lk.ijse.clothshop.bo.custom.OrderBo;
import lk.ijse.clothshop.bo.custom.supplierBo;
import lk.ijse.clothshop.db.DBConnection;
import lk.ijse.clothshop.dto.tm.Supplier;
import lk.ijse.clothshop.entity.supplier;

import java.sql.*;
import java.util.Properties;

public class SupplierFormController {

    private static final String URL = "jdbc:mysql://localhost:3306/clothshop";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public JFXTextField idtxt;
    public JFXTextField nametxt;
    public JFXTextField descriptiontxt;
    public JFXTextField contacttxt;
    public JFXTextField searchtxt;

    supplierBo supplierBo  = BoFactory.getBoFactory().getBo(BoFactory.BoType.SUPPLIER);

    public void OnActionSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = searchtxt.getText();

            supplier sup = supplierBo.search(search);
            if(sup!=null){
                idtxt.setText(sup.getSupplierid());
                nametxt.setText(sup.getSuppliername());
                contacttxt.setText(sup.getContact());
                descriptiontxt.setText(sup.getDescription());

                searchtxt.setText("");

            }


    }

    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = idtxt.getText();
        String name = nametxt.getText();
        String description = descriptiontxt.getText();
        String contact = contacttxt.getText();

            if(supplierBo.save(new Supplier(id,name,description,contact))){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier add successfully !").show();
                setDefault();


            }

    }

    public void OnActionDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String serch = idtxt.getText();

            if(supplierBo.delete(serch)){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted successfully !");

                setDefault();

                searchtxt.setText("");
            }

    }

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = idtxt.getText();
        String name = nametxt.getText();
        String description = descriptiontxt.getText();
        String contact = contacttxt.getText();

            if(supplierBo.update(new Supplier(id,name,description,contact))){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated successfully !").show();

                setDefault();

                searchtxt.setText("");

            }


    }

    private void setDefault(){
        idtxt.setText("");
        nametxt.setText("");
        descriptiontxt.setText("");
        contacttxt.setText("");
    }

}
