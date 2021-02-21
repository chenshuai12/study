package com.cs.designer.agency.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理对象
 * @author 陈帅
 */
public class ProxyFactory implements MethodInterceptor {


    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 为目标对象生成代理对象
     */
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer(); //工具类
        enhancer.setSuperclass(target.getClass());  //设置父类
        enhancer.setCallback(this);     //设置回调函数
        return enhancer.create();  //创建子类对象代理
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务");
        Object returnValue = method.invoke(target,objects);
        System.out.println("关闭事务");
        return null;
    }
}
