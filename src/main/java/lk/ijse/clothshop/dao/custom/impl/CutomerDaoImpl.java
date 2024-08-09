package lk.ijse.clothshop.dao.custom.impl;

import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.Customer;
import lk.ijse.clothshop.entity.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CutomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM customer");
        ArrayList<customer> data = new ArrayList<>();
        while (rst.next()) {
            data.add(new customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            ) {
            });
        }
        return data;
    }

    @Override
    public boolean save(customer dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO customer(CustID, CustName, Contact, UserId) VALUES(?, ?, ?, ?)",dto.getCustId(),dto.getCustName(),dto.getContact(),dto.getUserId());
    }

    @Override
    public boolean update(customer dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE employee SET CustName =  ?, Contact = ? WHERE CustID = ?",dto.getCustName(),dto.getContact(),dto.getCustId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM customer WHERE CustID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public customer search(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("SELECT * FROM customer WHERE CustID = ?",id);
    }
}
