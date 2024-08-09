package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.supplierBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.SupplierDao;
import lk.ijse.clothshop.dto.tm.Supplier;
import lk.ijse.clothshop.entity.supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBoImpl implements supplierBo {

    SupplierDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.SUPPLIER);

    @Override
    public ArrayList<supplier> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return obj.save(new supplier(dto.getSupplierid(),dto.getSuppliername(),dto.getDescription(),dto.getContact()));
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return obj.update(new supplier(dto.getSupplierid(),dto.getSuppliername(),dto.getDescription(),dto.getContact()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return obj.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public supplier search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}
