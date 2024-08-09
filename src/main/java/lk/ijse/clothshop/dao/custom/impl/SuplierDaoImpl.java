package lk.ijse.clothshop.dao.custom.impl;

import lk.ijse.clothshop.dao.custom.SupplierDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.entity.supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuplierDaoImpl implements SupplierDao {
    @Override
    public ArrayList<supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(supplier dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO supplier(SupplierId, SupplierName, Description, Contact) VALUES(?, ?, ?, ?)",
                dto.getSupplierid(),dto.getSuppliername(),dto.getDescription(),dto.getContact());
    }

    @Override
    public boolean update(supplier dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE supplier SET SupplierName =  ?, Description = ?, Contact = ? WHERE SupplierId = ?",
                dto.getSuppliername(),dto.getDescription(),dto.getContact(),dto.getSupplierid());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM supplier WHERE SupplierId = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM supplier WHERE SupplierId = ?",id);
        return rst.next() ? new supplier(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)) : null;
    }
}
