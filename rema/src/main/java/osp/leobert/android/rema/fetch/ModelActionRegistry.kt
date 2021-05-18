@file:Suppress("unchecked")

package osp.leobert.android.rema.fetch

import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.ActionHandler

/**
 * <p><b>Package:</b> osp.leobert.android.rema.fetch </p>
 * <p><b>Classname:</b> ModelActionRegistry </p>
 * Created by leobert on 2021/5/18.
 */
class ModelActionRegistry {
    private class Entry<Model, Result>(
        private val modelClass: Class<Model>,
        private val action: Action<Model, Result>,
        val actionHandler: ActionHandler<Model, Result>
    ) {

        fun <M, R> handles(modelClass: Class<M>, action: Action<M, R>): Boolean =
            this.modelClass.isAssignableFrom(modelClass) && this.action == action

    }

    private val actions: MutableList<Entry<*, *>> = arrayListOf()

    // FIXME: 2021/5/18 change to register ActionHandlerFactory instead of ActionHandler

    fun <Model, Result> append(
        modelClass: Class<Model>,
        action: Action<Model, Result>,
        actionHandler: ActionHandler<Model, Result>
    ) {
        actions.add(Entry(modelClass, action, actionHandler))
    }

    fun <Model, Result> getActionHandler(
        modelClass: Class<Model>,
        action: Action<Model, Result>
    ): ActionHandler<Model, Result>? {
        return actions.find {
            it.handles<Model, Result>(modelClass = modelClass, action = action)
        }?.actionHandler as? ActionHandler<Model, Result>?
    }
}