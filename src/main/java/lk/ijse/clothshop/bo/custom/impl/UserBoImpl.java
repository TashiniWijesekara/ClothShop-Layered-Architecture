package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.UserBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.UserDao;
import lk.ijse.clothshop.dto.tm.User;
import lk.ijse.clothshop.entity.user;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {

    UserDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);

    @Override
    public ArrayList<user> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return obj.save(new user(dto.getUserid(),dto.getUsername(),dto.getContact()));
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return obj.update(new user(dto.getUserid(),dto.getUsername(),dto.getContact()));
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
    public user search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}
