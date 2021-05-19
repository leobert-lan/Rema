package osp.leobert.android.rema.model

import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.core.Cancelable

/**
 * <p><b>Package:</b> osp.leobert.android.rema.model </p>
 * <p><b>Classname:</b> ModelActionHandlerDelegate </p>
 * Created by leobert on 2021/5/19.
 *
 * the real [Cancelable] returned to user
 *
 *
 */
// FIXME: 2021/5/19 设计有误，整个流程的cancelable 需要按照不同的状态进行对应的cancel，不一定是在加载状态，也可能是取缓存的状态，数据转换的状态（如果进行线程分离的话）
internal class ModelActionHandlerDelegate<Model, Result>(
    private val delegate: ModelActionHandler<Model, Result>,
    private val callback: ActionHandler.Callback<Model, Result>?
) : Cancelable {


    override fun cancel() {
        callback?.let {
            delegate.cancelByCallback(it)
        }
    }

}