package lk.ijse.clothshop.dao.custom.impl;

import lk.ijse.clothshop.dao.custom.EmployeeDao;
import lk.ijse.clothshop.dao.custom.impl.util.SqlUtil;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.entity.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public ArrayList<employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<employee> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            data.add(new employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ) {
            });
        }
        return data;
    }

    @Override
    public boolean save(employee dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO employee(EmpId, FirstName, LastName, EmpAddress, DateOfJoing, Contact, NIC, UserId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", dto.getEmpId(), dto.getFirstName(), dto.getLastName(), dto.getEmpAddress(), dto.getDateOfJoing(),dto.getContact(),dto.getNic(),dto.getUserId());
    }

    @Override
    public boolean update(employee dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE employee SET FirstName =  ?, LastName = ?, EmpAddress = ?, Contact = ?, NIC = ? WHERE EmpId = ?", dto.getFirstName(),dto.getLastName(),dto.getEmpAddress(),dto.getContact(),dto.getNic(),dto.getEmpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM employee WHERE EmpId = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM employee WHERE EmpId = ?",id);
        return rst.next() ? new employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8)) : null;
    }
}
