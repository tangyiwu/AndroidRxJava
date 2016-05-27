package com.haizhi.tty.androidrxjavalib.operators;

import com.haizhi.tty.androidrxjavalib.Subject;
import com.haizhi.tty.androidrxjavalib.Subscriber;
import com.haizhi.tty.androidrxjavalib.functions.Function1;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public class OperatorMap<T, R> implements Subject.Operator<R, T> {
    private Function1<? super T, ? extends R> convert;

    public OperatorMap(Function1<? super T, ? extends R> convert) {
        this.convert = convert;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> var) {
        return new Subscriber<T>(var) {
            @Override
            public void onNext(T state) {

                var.onNext(OperatorMap.this.convert.call(state));
            }
        };
    }
}
