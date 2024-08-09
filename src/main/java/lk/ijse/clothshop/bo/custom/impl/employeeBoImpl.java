package lk.ijse.clothshop.bo.custom.impl;

import lk.ijse.clothshop.bo.custom.employeeBo;
import lk.ijse.clothshop.dao.DaoFactory;
import lk.ijse.clothshop.dao.custom.CustomerDao;
import lk.ijse.clothshop.dao.custom.EmployeeDao;
import lk.ijse.clothshop.dto.tm.Employeetable;
import lk.ijse.clothshop.entity.employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class employeeBoImpl implements employeeBo {

    EmployeeDao obj = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public ArrayList<employee> getAll() throws SQLException, ClassNotFoundException {
        return obj.getAll();
    }

    @Override
    public boolean save(Employeetable dto) throws SQLException, ClassNotFoundException {
        return obj.save(new employee(dto.getEmpId(), dto.getFirstName(), dto.getLastName(), dto.getEmpAddress(), dto.getDateOfJoing(),dto.getContact(),dto.getNic(),dto.getUserId()));
    }

    @Override
    public boolean update(Employeetable dto) throws SQLException, ClassNotFoundException {
        return obj.update(new employee(dto.getEmpId(), dto.getFirstName(), dto.getLastName(), dto.getEmpAddress(), dto.getDateOfJoing(),dto.getContact(),dto.getNic(),dto.getUserId()));
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
    public employee search(String id) throws SQLException, ClassNotFoundException {
        return obj.search(id);
    }
}
