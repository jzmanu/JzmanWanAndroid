package com.manu.wanandroid.http.rx;

import com.manu.wanandroid.http.BaseResultBean;
import com.manu.wanandroid.http.exception.MException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Desc: RxUtil
 * @Author: jzman
 * @Date: 2019/5/10 0010 14:53
 */
public class RxUtil {

    /**
     * 线程调度
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 数据剥离
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResultBean<T>, T> rxHandlerResult() {
        return observable -> observable.flatMap((Function<BaseResultBean<T>, ObservableSource<T>>) baseResultBean -> {
            if (baseResultBean.getErrorCode() == 0) {
                return rxCreateObservable(baseResultBean.getData());
            }
            MException mException = new MException(baseResultBean.getErrorCode(), baseResultBean.getErrorMsg());
            return Observable.error(mException);
        });
    }

    private static <T> Observable<T> rxCreateObservable(T data) {
        return Observable.create(emitter -> {
            try {
                if (data != null) emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
