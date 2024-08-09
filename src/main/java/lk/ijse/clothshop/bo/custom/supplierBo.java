package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.dto.tm.Supplier;
import lk.ijse.clothshop.entity.order;
import lk.ijse.clothshop.entity.supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface supplierBo extends SuperBo {
    ArrayList<supplier> getAll() throws SQLException, ClassNotFoundException;
    boolean save(Supplier dto) throws SQLException, ClassNotFoundException;
    boolean update(Supplier dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    supplier search(String id) throws SQLException, ClassNotFoundException;
}
