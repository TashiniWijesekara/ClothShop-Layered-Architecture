package lk.ijse.clothshop.dao.custom.impl;

import com.mysql.cj.protocol.Resultset;
import lk.ijse.clothshop.dao.custom.ItemDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.entity.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public ArrayList<item> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(item dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT  INTO item(ItemID, ItemName, Quantity, ClothBrand) VALUES(?, ?, ?, ?)",
                dto.getItemId(),dto.getItemName(),dto.getQuntity(),dto.getClothBrand());
    }

    @Override
    public boolean update(item dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE item SET ItemName = ?, Quantity = ?, ClothBrand = ? WHERE ItemID = ?",
                dto.getItemName(),dto.getQuntity(),dto.getClothBrand(),dto.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM item WHERE ItemID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT *FROM item WHERE ItemID = ?",id);
        return rst.next() ? new item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4)):null;
    }
}
