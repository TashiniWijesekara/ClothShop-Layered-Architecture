package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.OrderBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.OrderDao;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.entity.order;

import java.sql.SQLException;
import java.util.ArrayList;

public class    OrderBoImpl implements OrderBo {

    OrderDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ORDER);

    @Override
    public ArrayList<order> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
        return obj.save(new order(dto.getOrderId(),dto.getDate(),dto.getPrice(),dto.getCustId(),dto.getItem(),dto.getItemPrice(),dto.getBonus(),dto.getAmount()));
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return obj.update(new order(dto.getOrderId(),dto.getDate(),dto.getPrice(),dto.getCustId(),dto.getItem(),dto.getItemPrice(),dto.getBonus(),dto.getAmount()));
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
    public order search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}