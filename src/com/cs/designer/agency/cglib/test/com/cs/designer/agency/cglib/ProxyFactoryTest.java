package com.cs.designer.agency.cglib;

import com.cs.designer.agency.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProxyFactoryTest {
    @Test
    public void testCglibProxy(){
        UserDao target = new UserDao(); //目标对象
        System.out.println(target.getClass());
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();  //执行代理对象方法
    }
}