package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.dto.tm.Item;
import lk.ijse.clothshop.entity.employee;
import lk.ijse.clothshop.entity.item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    ArrayList<item> getAll() throws SQLException, ClassNotFoundException;
    boolean save(Item dto) throws SQLException, ClassNotFoundException;
    boolean update(Item dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    item search(String id) throws SQLException, ClassNotFoundException;
}
