package lk.ijse.clothshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.clothshop.bo.BoFactory;
import lk.ijse.clothshop.bo.custom.ItemBo;
import lk.ijse.clothshop.dto.tm.Item;
import lk.ijse.clothshop.entity.item;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.Properties;

public class ItemFormController {

    private static final String URL = "jdbc:mysql://localhost:3306/clothshop";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public JFXTextField quantitytxt;
    public JFXButton serch;
    public JFXButton add;
    public JFXButton delet;
    public JFXButton updte;
    public JFXTextField itemtxt;
    public JFXTextField itemname;
    public JFXTextField clothbrandtxt;
    public JFXTextField searchtxt;

    ItemBo itemBo  = BoFactory.getBoFactory().getBo(BoFactory.BoType.ITEM);

    public void OnActionSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = searchtxt.getText();

            item item = itemBo.search(search);
            if(item!=null){
                itemtxt.setText(item.getItemId());
                itemname.setText(item.getItemName());
                quantitytxt.setText(String.valueOf(item.getQuntity()));
                clothbrandtxt.setText(item.getClothBrand());

                searchtxt.setText("");

            }else {
                new Alert(Alert.AlertType.ERROR, "Please check and enter the correct id !").show();
            }

    }

    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = itemtxt.getText();
        String name = itemname.getText();
        int quantity = Integer.parseInt(quantitytxt.getText());
        String barnd = clothbrandtxt.getText();

            if(itemBo.save(new Item(id,name,quantity,barnd))){
                new Alert(Alert.AlertType.CONFIRMATION, "Item add successfully !").show();

                setDefault();

            }
    }

    public void OnActionDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = itemtxt.getText();

            if(itemBo.delete(id)){
                new Alert(Alert.AlertType.CONFIRMATION, "Item remove successfull !").show();

                setDefault();

            }

    }

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = itemtxt.getText();
        String name = itemname.getText();
        int quantity = Integer.parseInt(quantitytxt.getText());
        String brand = clothbrandtxt.getText();

            if (itemBo.update(new Item(id,name,quantity,brand))){
                new Alert(Alert.AlertType.CONFIRMATION, "Item update succcessfully !").show();

                setDefault();
            }

    }

    private void setDefault(){
        itemtxt.setText("");
        itemname.setText("");
        quantitytxt.setText("");
        clothbrandtxt.setText("");
    }
}
