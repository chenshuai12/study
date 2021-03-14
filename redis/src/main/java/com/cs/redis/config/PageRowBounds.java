package com.cs.redis.config;


import lombok.ToString;
import org.apache.ibatis.session.RowBounds;


@ToString
public class PageRowBounds extends RowBounds {
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    private Long total;
    public PageRowBounds(int offset,int limit){
        super(offset,limit);
    }
}
