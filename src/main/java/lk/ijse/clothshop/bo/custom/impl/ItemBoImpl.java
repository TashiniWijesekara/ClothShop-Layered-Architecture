package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.ItemBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.ItemDao;
import lk.ijse.clothshop.dto.tm.Item;
import lk.ijse.clothshop.entity.item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ITEM);

    @Override
    public ArrayList<item> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return obj.save(new item(dto.getItemId(),dto.getItemName(),dto.getQuntity(),dto.getClothBrand()));
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return obj.update(new item(dto.getItemId(),dto.getItemName(),dto.getQuntity(),dto.getClothBrand()));
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
    public item search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}
