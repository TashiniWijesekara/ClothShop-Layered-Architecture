package lk.ijse.clothshop.dao;

import lk.ijse.clothshop.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory(){
        return daoFactory==null ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType {
        CUSTOMER,EMPLOYEE,ITEM,ORDER,SUPPLIER,USER
    }

    public <SuperDao>SuperDao getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (SuperDao) new CutomerDaoImpl();
            case EMPLOYEE:
                return (SuperDao) new EmployeeDaoImpl();
            case ITEM:
                return (SuperDao) new ItemDaoImpl();
            case ORDER:
                return (SuperDao) new OrderDaoImpl();
            case SUPPLIER:
                return (SuperDao) new SuplierDaoImpl();
            case USER:
                return (SuperDao) new UserDaoImpl();
            default:
                return null;
        }
    }
}
