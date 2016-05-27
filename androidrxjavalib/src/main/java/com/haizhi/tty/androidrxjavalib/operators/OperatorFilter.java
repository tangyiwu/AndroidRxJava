package com.haizhi.tty.androidrxjavalib.operators;

import com.haizhi.tty.androidrxjavalib.Subject;
import com.haizhi.tty.androidrxjavalib.Subscriber;
import com.haizhi.tty.androidrxjavalib.functions.Function1;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public class OperatorFilter<T> implements Subject.Operator<T, T>  {
    Function1<? super T, Boolean> function;

    public OperatorFilter(Function1<? super T, Boolean> function) {
        this.function = function;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> var) {
        return new Subscriber<T>(var) {
            @Override
            public void onNext(T state) {
                if (OperatorFilter.this.function.call(state)) {
                    var.onNext(state);
                }
            }
        };
    }
}
