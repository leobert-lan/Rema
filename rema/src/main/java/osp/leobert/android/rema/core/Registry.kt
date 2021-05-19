package osp.leobert.android.rema.core

import osp.leobert.android.rema.Rema
import osp.leobert.android.rema.factory.ModelActionHandlerFactory
import osp.leobert.android.rema.model.ModelActionRegistry

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> Registry </p>
 * Created by leobert on 2021/5/18.
 */
class Registry(val rema: Rema) {
    private val modelActionRegistry: ModelActionRegistry = ModelActionRegistry(rema)

    fun <Model, Result> appendModelAction(
        modelClass: Class<Model>,
        action: Action<Model, Result>,
        actionHandlerFactory: ModelActionHandlerFactory<Model, Result>
    ) {
        modelActionRegistry.append(modelClass, action, actionHandlerFactory)
    }



}