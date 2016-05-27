package com.haizhi.tty.androidrxjavalib;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public interface Observer<T> {
    public void onNext(T state);
}
