package lk.ijse.clothshop.dao.custom.impl;

import com.mysql.cj.protocol.Resultset;
import lk.ijse.clothshop.dao.custom.UserDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.User;
import lk.ijse.clothshop.entity.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public ArrayList<user> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM user");
        ArrayList<user> data = new ArrayList<>();
        while (rst.next()) {
            data.add(new user(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ) {
            });
        }

        return data;
    }

    @Override
    public boolean save(user dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO user(UserId, UserName, Contact) VALUES(?, ?, ?)",dto.getUserId(),dto.getUserName(),dto.getContact());
    }

    @Override
    public boolean update(user dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE user SET UserName = ?, Contact = ? WHERE UserId = ?",dto.getUserName(),dto.getContact(),dto.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM user WHERE UserId = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public user search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT *FROM user WHERE UserId = ?",id);
        return rst.next() ? new user(rst.getString(1),rst.getString(2),rst.getString(3)):null;
    }
}
