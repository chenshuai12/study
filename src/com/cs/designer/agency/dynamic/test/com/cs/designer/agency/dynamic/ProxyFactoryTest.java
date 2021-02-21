package com.cs.designer.agency.dynamic;

import com.cs.designer.agency.IUserDao;
import com.cs.designer.agency.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProxyFactoryTest {
    @Test
    public void test(){
        IUserDao userDao = new UserDao();
        System.out.println(userDao.getClass());
        IUserDao proxy = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        proxy.save();
    }
}