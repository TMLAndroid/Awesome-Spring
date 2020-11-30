package com.luban.manager;

import org.springframework.stereotype.Component;

@Component
public class UserClazz {
    private OrderClazz orderClazz;

    public OrderClazz getOrderClazz() {
        return orderClazz;
    }

    public void setOrderClazz(OrderClazz orderClazz) {
        this.orderClazz = orderClazz;
    }
}
