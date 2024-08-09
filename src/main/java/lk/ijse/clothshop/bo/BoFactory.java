package lk.ijse.clothshop.bo;

import lk.ijse.clothshop.bo.custom.impl.*;
import lk.ijse.clothshop.dao.SuperDao;
import lk.ijse.clothshop.dao.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory(){
        return boFactory ==null ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoType {
        CUSTOMER,EMPLOYEE,ITEM,ORDER,SUPPLIER,USER
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case EMPLOYEE:
                return (T) new employeeBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();
            case ORDER:
                return (T) new OrderBoImpl();
            case SUPPLIER:
                return (T) new SupplierBoImpl();
            case USER:
                return (T) new UserBoImpl();
            default:
                return null;
        }
    }
}
