package com.haizhi.tty.androidrxjavalib;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public abstract class Subscriber<T> implements Observer<T>, Subscrition {
    private Subscriber<?> subscriber;

    public Subscriber(Subscriber<?> subscriber) {
        this.subscriber = subscriber;
    }

    public Subscriber() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public boolean isUnSubscribed() {
        return false;
    }
}
