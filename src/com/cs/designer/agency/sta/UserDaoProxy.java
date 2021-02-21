package com.cs.designer.agency.sta;
import com.cs.designer.agency.IUserDao;



/**
 * @author 陈帅
 * 代理对象
 */
public class UserDaoProxy implements IUserDao {
    /**
     * 保存被代理对象的引用
     */

    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     *  保存数据
     */
    @Override
    public void save() {
        System.out.println("开始保存数据");
        userDao.save();
        System.out.println("保存数据结束");
    }
}
