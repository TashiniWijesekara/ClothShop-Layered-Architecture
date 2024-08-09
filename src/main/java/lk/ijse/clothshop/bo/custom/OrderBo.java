package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dto.tm.Item;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.entity.item;
import lk.ijse.clothshop.entity.order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {
    ArrayList<order> getAll() throws SQLException, ClassNotFoundException;
    boolean save(Orders dto) throws SQLException, ClassNotFoundException;
    boolean update(Orders dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    order search(String id) throws SQLException, ClassNotFoundException;
}
