package com.haizhi.tty.androidrxjavalib;

import com.haizhi.tty.androidrxjavalib.functions.Action1;
import com.haizhi.tty.androidrxjavalib.functions.Function1;
import com.haizhi.tty.androidrxjavalib.operators.OperatorFilter;
import com.haizhi.tty.androidrxjavalib.operators.OperatorMap;

/**
 * @author tangyiwu
 * @date 16/5/26
 */
public class Subject<T> {
    private OnSubscible<T> onSubscible;

    public Subject(OnSubscible<T> onSubscible) {
        this.onSubscible = onSubscible;
    }

    public static <T> Subject<T> create(OnSubscible<T> onSubscible) {
        return new Subject<>(onSubscible);
    }

    public void subscribe(Subscriber<T> subscriber) {
        if (subscriber == null) {
            throw new NullPointerException("Observer can not be null");
        } else if (this.onSubscible == null) {
            throw new IllegalArgumentException("onSubscibe function can not be null");
        } else {
            this.onSubscible.call(subscriber);
        }
    }

    public <R> Subject<R> filter(Function1<? super T, Boolean> function1) {
        return lift(new OperatorFilter(function1));
    }

    public <R> Subject<R> map(Function1<? super T, ? extends R> function1) {
        return lift(new OperatorMap<T, R>(function1));
    }

    private <R> Subject<R> lift(final Operator<? extends R, ? super T> operator) {
        return new Subject<>(new OnSubscible<R>() {
            @Override
            public void call(Subscriber<? super R> var) {
                Subscriber<? super T> subscriber = operator.call(var);
                Subject.this.onSubscible.call(subscriber);
            }
        });
    }

    public interface OnSubscible<T> extends Action1<Subscriber<? super T>> {

    }

    public interface Operator<R, T> extends Function1<Subscriber<? super R>, Subscriber<? super T>> {

    }
}
