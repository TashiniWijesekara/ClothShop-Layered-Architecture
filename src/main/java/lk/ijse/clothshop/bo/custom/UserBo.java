package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.dto.tm.User;
import lk.ijse.clothshop.entity.order;
import lk.ijse.clothshop.entity.user;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBo extends SuperBo {
    ArrayList<user> getAll() throws SQLException, ClassNotFoundException;
    boolean save(User dto) throws SQLException, ClassNotFoundException;
    boolean update(User dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    user search(String id) throws SQLException, ClassNotFoundException;
}
