package com.cs.designer.decorator;

/**
 * 抽象装饰者
 * 所有的调料装饰者都必须重新实现getDescription方法
 */
public abstract class CondimentDesorator extends Beverage{
    @Override
    public abstract String getDescription();
}
