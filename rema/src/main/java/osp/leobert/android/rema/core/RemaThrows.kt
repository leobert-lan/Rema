package osp.leobert.android.rema.core

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> RemaThrows </p>
 * Created by leobert on 2021/5/19.
 */
object RemaThrows {
    class EmptyCallback : Throwable()

    fun callbackEmpty() = EmptyCallback()
}