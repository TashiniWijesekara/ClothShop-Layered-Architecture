package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.customerBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dto.tm.Customer;
import lk.ijse.clothshop.entity.customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements customerBo {

    CustomerDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public ArrayList<customer> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return obj.save(new customer(dto.getCustomerid(),dto.getCutomername(),dto.getContact(),dto.getUserid()));
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return obj.update(new customer(dto.getCustomerid(),dto.getCutomername(),dto.getContact(),dto.getUserid()));
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
    public customer search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}
