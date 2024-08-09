package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.Customer;
import lk.ijse.clothshop.entity.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface customerBo extends SuperBo {
     ArrayList<customer> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Customer dto) throws SQLException, ClassNotFoundException;
     boolean update(Customer dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
     customer search(String id) throws SQLException, ClassNotFoundException;
}
