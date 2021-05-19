package osp.leobert.android.rema.model

import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.core.RemaThrows
import osp.leobert.android.rema.safe

/**
 * <p><b>Package:</b> osp.leobert.android.rema.model </p>
 * <p><b>Classname:</b> ModelActionHandler </p>
 * Created by leobert on 2021/5/19.
 *
 * Handler to handle the action for specified model
 */
abstract class ModelActionHandler<Model, Result>(
    callback: Callback<Model, Result>
) : ActionHandler<Model, Result>(
    callback
) {

    fun cancelByCallback(callback: Callback<Model, Result>) {
        try {

            this.callback?.cancel(callback)
        } catch (thr: RemaThrows.EmptyCallback) {
            this.cancel()
        }
    }

    final override fun cancel() {

        //todo change state
        safe {
            cancelAction()
        }

    }

    abstract fun cancelAction()
}