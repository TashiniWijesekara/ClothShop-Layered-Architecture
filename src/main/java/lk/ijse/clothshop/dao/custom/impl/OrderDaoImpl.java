package lk.ijse.clothshop.dao.custom.impl;

import lk.ijse.clothshop.dao.custom.OrderDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.Orders;
import lk.ijse.clothshop.entity.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM orders");
        ArrayList<order> data = new ArrayList<>();
        while (rst.next()) {
            data.add(new order(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getString(8)

            ) {
            });
        }
        return data;
    }

    @Override
    public boolean save(order dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO orders(OrderId, OrderDate, Price, CustID, item, itemprice, bonus, amount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", dto.getOrderId(),dto.getDate(),dto.getPrice(),dto.getCustId(),dto.getItem(),dto.getItemPrice(),dto.getBonus(),dto.getAmount());
    }

    @Override
    public boolean update(order dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE orders SET OrderDate = ?, Price = ?, CustID = ?, item = ?, itemprice = ?, bonus = ?, amount = ? WHERE OrderId = ?",dto.getDate(),dto.getPrice(),dto.getCustId(),dto.getItem(),dto.getItemPrice(),dto.getBonus(),dto.getAmount(), dto.getOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM orders WHERE OrderId = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public order search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM orders WHERE OrderId = ?",id);
        return rst.next() ? new order(rst.getString(1),rst.getDate(2),rst.getDouble(3),rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getDouble(7),rst.getString(8)) : null;
    }
}
