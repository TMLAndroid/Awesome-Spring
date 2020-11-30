package com.example.luban.thread;

public class Account {
    private int money = 200;
    public void transfer(Account target){
        Allocator.getInstance().apply(this,target);
        synchronized (this){
            synchronized (target){
                this.money -= 100;
                target.money +=100;
                System.out.println("left: "+money+" "+target.money);
                Allocator.getInstance().free(this,target);
            }
        }

    }
}
