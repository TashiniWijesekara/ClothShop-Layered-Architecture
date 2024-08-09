package lk.ijse.clothshop.bo.custom;

import lk.ijse.clothshop.bo.SuperBo;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.entity.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface employeeBo extends SuperBo {
     ArrayList<employee> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Employeetable dto) throws SQLException, ClassNotFoundException;
     boolean update(Employeetable dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
     employee search(String id) throws SQLException, ClassNotFoundException;
}
