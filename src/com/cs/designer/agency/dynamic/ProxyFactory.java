package com.cs.designer.agency.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理对象
 * @author 陈帅
 */
public class ProxyFactory {
    /**
     * 维护一个目标对象
     */
    private Object target;
    public ProxyFactory(Object o){
        this.target = o;
    }

    /**
     * 为目标对象生成代理对象
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
            System.out.println("写入数据前");
            //执行目标对象方法
            Object returnValue = method.invoke(target,args);
            System.out.println("写入数据后");
            return null;
        });
    }
}
