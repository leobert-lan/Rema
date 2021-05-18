package osp.leobert.android.rema.core

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> ActionHandler </p>
 * Created by leobert on 2021/5/18.
 */
abstract class ActionHandler<Model, Result> {

    abstract fun handle(model: Model,request: Request<Model, Result>)

    protected fun onStart(request: Request<Model, Result>) {

    }

    protected fun onSuccess(request: Request<Model, Result>) {

    }

    protected fun onFailure(request: Request<Model, Result>, exception: Exception) {

    }
}