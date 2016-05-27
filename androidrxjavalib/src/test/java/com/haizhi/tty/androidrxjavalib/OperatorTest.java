package com.haizhi.tty.androidrxjavalib;

import com.haizhi.tty.androidrxjavalib.functions.Function1;

import org.junit.Test;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public class OperatorTest {
    @Test
    public void filterTest() {
        Subject<String> subject = Subject.create(new Subject.OnSubscible<String>() {

            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("abcdefgh");
            }
        });

        Subject<String> newSubject = subject.map(new Function1<String, String>() {
            @Override
            public String call(String var) {
                return var + "_map";
            }
        });

        Subject<String> threeSubject = newSubject.filter(new Function1<String, Boolean>() {
            @Override
            public Boolean call(String var) {
                return var.length() > 10;
            }
        });

        threeSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state);
            }
        });
    }

    @Test
    public void mapTest() {
        Subject<String> subject = Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("ok");
            }
        });

        Subject<String> newSubject = subject.map(new Function1<String, String>() {
            @Override
            public String call(String var) {
                return var + "_map";
            }
        });

        Subject<String> threeSubject = newSubject.map(new Function1<String, String>() {
            @Override
            public String call(String var) {
                return var + "||||||||||";
            }
        });

        threeSubject.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state);
            }
        });
    }

    @Test
    public void createTest() {
        Subject.create(new Subject.OnSubscible<String>() {
            @Override
            public void call(Subscriber<? super String> var) {
                var.onNext("ok");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String state) {
                System.out.println(state + "====");
            }
        });
    }
}
