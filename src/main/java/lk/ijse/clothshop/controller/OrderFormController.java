package lk.ijse.clothshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import lk.ijse.clothshop.bo.BoFactory;
import lk.ijse.clothshop.bo.custom.ItemBo;
import lk.ijse.clothshop.bo.custom.OrderBo;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.entity.order;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderFormController implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/clothshop";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public JFXTextField orderidtxt;
    public JFXTextField customerid;
    public JFXTextField pricetxt;
    public DatePicker datetxt;
    public JFXTextField searchtxt;
    public JFXComboBox customeridcombotxt;
    public JFXTextField amounttxt;
    public JFXComboBox itemtxt;
    public JFXTextField itemprice;
    public JFXTextField bonus;

    OrderBo orderBo  = BoFactory.getBoFactory().getBo(BoFactory.BoType.ORDER);

    public void OnActionSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = searchtxt.getText();
            order order =  orderBo.search(search);
            if(order!=null){
                datetxt.setValue(LocalDate.parse((CharSequence) order.getDate()));
                orderidtxt.setText(order.getOrderId());
                customeridcombotxt.setValue(order.getCustId());
                pricetxt.setText(String.valueOf(order.getPrice()));
                itemtxt.setValue(order.getItem());
                itemprice.setText(String.valueOf(order.getItemPrice()));
                bonus.setText(String.valueOf(order.getBonus()));
                amounttxt.setText(order.getAmount());

                searchtxt.setText("");

            }

    }

    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String orderid = orderidtxt.getText();
        String date = String.valueOf(datetxt.getValue());
        double price = Double.parseDouble(pricetxt.getText());
        String customrid = String.valueOf(customeridcombotxt.getValue());
        String itm = String.valueOf(itemtxt.getValue());
        double itmpr = Double.parseDouble(itemprice.getText());
        double bons = Double.parseDouble(bonus.getText());
        String amount = amounttxt.getText();
        Date dt = Date.valueOf(date);

        if(isint(orderid)& isint(customrid)) {
                if (orderBo.save(new Orders(orderid,dt,price,customrid,itm,itmpr,bons,amount))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order add successfully !").show();
                    setDefault();
                }

        }else {
            new Alert(Alert.AlertType.WARNING, "Please check & enter valid value !").show();
        }


    }

    public void OnActionDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String search = orderidtxt.getText();

            if (orderBo.delete(search)){
                new Alert(Alert.AlertType.CONFIRMATION, "Order add successfully !").show();
                setDefault();
            }


    }

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String orderid = orderidtxt.getText();
        String date = String.valueOf(datetxt.getValue());
        double price = Double.parseDouble(pricetxt.getText());
        String customrid = String.valueOf(customeridcombotxt.getValue());
        String itm = String.valueOf(itemtxt.getValue());
        double itprice  = Double.parseDouble(itemprice.getText());
        double bons  = Double.parseDouble(bonus.getText());
        String amount = amounttxt.getText();
        Date dt = Date.valueOf(date);

            if (orderBo.update(new Orders(orderid,dt,price,customrid,itm,itprice,bons,amount))){
                new Alert(Alert.AlertType.CONFIRMATION, "Order update successfully !").show();

                setDefault();

            }

    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        List<String> items = new ArrayList<>();
        List<String> item = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, props)){
            String sql = "SELECT * FROM customer";

            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                String id = resultSet.getString(1);
                items.add(id);
            }

            customeridcombotxt.getItems().addAll(items);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, props)){
            String sqll = "SELECT * FROM item";

            PreparedStatement pstm = conn.prepareStatement(sqll);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                String id = resultSet.getString(2);
                item.add(id);
            }

            itemtxt.getItems().addAll(item);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void OnActionplaceorder(ActionEvent actionEvent) {

    }

    public void OnActionpayment(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/placeorder_form.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void plusbtnonaction(ActionEvent actionEvent) {


        if (bonus.getText()!=null){
            double iteprice = Double.parseDouble(itemprice.getText());
            double bons = Double.parseDouble(bonus.getText());
            int amount = Integer.parseInt(amounttxt.getText());

            double min = ((iteprice*amount)/100)*bons;
            double tot = (iteprice*amount)-min;

            pricetxt.setText(String.valueOf(tot));

        }else {
            double iteprice = Double.parseDouble(itemprice.getText());
            int amount = Integer.parseInt(amounttxt.getText());

            double tot = (iteprice*amount);

            pricetxt.setText(String.valueOf(tot));
        }
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

    public void OnActioncheckorder(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Ordertable.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void setDefault(){
        orderidtxt.setText("");
        datetxt.setValue(null);
        pricetxt.setText("");
        customeridcombotxt.setValue(null);
        itemtxt.setValue(null);
        itemprice.setText("");
        bonus.setText("");
        amounttxt.setText("");

        searchtxt.setText("");
    }

}
