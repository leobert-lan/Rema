package osp.leobert.android.rema.factory

import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.model.ModelActionHandler

/**
 * <p><b>Package:</b> osp.leobert.android.rema.factory </p>
 * <p><b>Classname:</b> ModelActionHandlerFactory </p>
 * Created by leobert on 2021/5/19.
 *
 * factory to create instance of [ModelActionHandler] to handle action
 */
interface ModelActionHandlerFactory<Model, Result> {

    fun create(
        modelClass: Class<Model>,
        resultClass: Class<Result>,
        action: Action<Model, Result>,
        callback: ActionHandler.Callback<Model, Result>
    ): ModelActionHandler<Model, Result>
}