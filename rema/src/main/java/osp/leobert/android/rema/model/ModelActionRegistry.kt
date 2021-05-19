@file:Suppress("unchecked", "unused")

package osp.leobert.android.rema.model

import osp.leobert.android.rema.Rema
import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.core.RemaThrows
import osp.leobert.android.rema.factory.ModelActionHandlerFactory
import osp.leobert.android.rema.takeIfInstance

/**
 * <p><b>Package:</b> osp.leobert.android.rema.fetch </p>
 * <p><b>Classname:</b> ModelActionRegistry </p>
 * Created by leobert on 2021/5/18.
 */
class ModelActionRegistry(val rema: Rema) {
    private class Entry<Model, Result>(
        private val modelClass: Class<Model>,
        private val action: Action<Model, Result>,
        val actionHandlerFactory: ModelActionHandlerFactory<Model, Result>
    ) {

        fun <M, R> handles(modelClass: Class<M>, action: Action<M, R>): Boolean =
            this.modelClass.isAssignableFrom(modelClass) && this.action == action

        override fun toString(): String {
            return "Entry(modelClass=$modelClass,\n action=$action,\n actionHandlerFactory=$actionHandlerFactory)"
        }


    }

    private val entries: MutableList<Entry<*, *>> = arrayListOf()

    fun <Model, Result> append(
        modelClass: Class<Model>,
        action: Action<Model, Result>,
        actionHandlerFactory: ModelActionHandlerFactory<Model, Result>
    ) {
        entries.add(Entry(modelClass, action, actionHandlerFactory))
    }

    fun clear() {
        entries.clear()
    }

    fun <Model, Result> getActionHandler(
        modelClass: Class<Model>,
        resultClass: Class<Result>,
        action: Action<Model, Result>
    ): ActionHandler<Model, Result>? {
        return entries.find {
            it.handles(modelClass = modelClass, action = action)
        }.apply {
            //        1. missing register
            if (this == null)
                throw RemaThrows.missingRegisterModelAction(modelClass, action)
        }?.run {
            takeIfInstance<Entry<Model, Result>>().apply {
                //        2. error register
                if (this == null)
                    throw RemaThrows.errorRegisterModelAction(this@run.toString())
            }?.run {
                this.actionHandlerFactory.create(
                    modelClass = modelClass,
                    resultClass = resultClass,
                    action = action,
                    callback = ActionHandler.ComposeCallback()
                )

            }
        }
    }
}