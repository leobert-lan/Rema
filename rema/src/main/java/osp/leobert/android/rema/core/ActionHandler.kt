package osp.leobert.android.rema.core

import kotlin.jvm.Throws

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> ActionHandler </p>
 * Created by leobert on 2021/5/18.
 */
abstract class ActionHandler<Model, Result>(
    val callback: Callback<Model, Result>?
) : Cancelable {

    internal interface ICallback<Model, Result> {
        fun onStart(request: Request<Model, Result>)

        fun onSuccess(request: Request<Model, Result>, result: Result?)

        fun onFailure(request: Request<Model, Result>, exception: Exception)

        fun onCanceled()
    }

    abstract class Callback<Model, Result> : ICallback<Model, Result> {
        internal open fun cancel(callback: Callback<*, *>) {

        }
    }

    class ComposeCallback<Model, Result>() :
        Callback<Model, Result>() {
        private val children = arrayListOf<Callback<Model, Result>>()

        override fun onStart(request: Request<Model, Result>) {
            children.forEach { it.onStart(request) }
            children.clear()
        }

        override fun onSuccess(request: Request<Model, Result>, result: Result?) {
            children.forEach { it.onSuccess(request, result) }
            children.clear()
        }

        override fun onFailure(request: Request<Model, Result>, exception: Exception) {
            children.forEach { it.onFailure(request, exception) }
            children.clear()
        }

        override fun onCanceled() {
            children.forEach { it.onCanceled() }
            children.clear()
        }

        @Throws(RemaThrows.EmptyCallback::class)
        override fun cancel(callback: Callback<*, *>) {
            when {
                children == callback -> {
                    callback.onCanceled()
                    throw RemaThrows.callbackEmpty()
                }
                children.remove(callback) -> {
                    callback.onCanceled()
                    if (children.isEmpty()) {
                        throw RemaThrows.callbackEmpty()
                    }
                }
                else -> {
                    // TODO: 2021/5/19 log
                }
            }

        }

    }

    abstract fun handle(model: Model, request: Request<Model, Result>)

    protected fun onStart(request: Request<Model, Result>) {

        callback?.onStart(request)
    }

    protected fun onSuccess(request: Request<Model, Result>, result: Result?) {

        callback?.onSuccess(request, result)
    }

    protected fun onFailure(request: Request<Model, Result>, exception: Exception) {

        callback?.onFailure(request, exception)
    }
}