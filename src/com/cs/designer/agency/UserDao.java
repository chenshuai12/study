package com.cs.designer.agency;


/**
 * @author 陈帅
 * 目标对象
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("保存数据进入数据库");
    }
}
