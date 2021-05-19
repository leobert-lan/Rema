package osp.leobert.android.rema.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import osp.leobert.android.rema.Rema
import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.core.Request
import osp.leobert.android.rema.factory.ModelActionHandlerFactory


/**
 * <p><b>Package:</b> osp.leobert.android.rema.model </p>
 * <p><b>Classname:</b> ModelActionRegistryTest </p>
 * Created by leobert on 2021/5/19.
 */
class ModelActionRegistryTest {

    var target: ModelActionRegistry = ModelActionRegistry(Rema())

    open class M {

        class LoadAction(callback: Callback<M, R>) : ModelActionHandler<M, R>(
            callback
        ) {
            override fun cancelAction() {
            }

            override fun handle(model: M, request: Request<M, R>) {
            }

        }
    }

    class M1 : M()

    open class R

    class R1 : R()

    object ActionMR : Action<M, R>()

    @Before
    fun start() {
        target.clear()

        target.append(
            M::class.java,
            ActionMR,
            object : ModelActionHandlerFactory<M, R> {
                override fun create(
                    modelClass: Class<M>,
                    resultClass: Class<R>,
                    action: Action<M, R>,
                    callback: ActionHandler.Callback<M, R>
                ): ModelActionHandler<M, R> {
                    return M.LoadAction(callback)
                }
            }
        )

    }

    @Test
    fun test_MR() {
        val handler = target.getActionHandler(
            M::class.java,
            R::class.java,
            ActionMR
        )

        assertEquals(M.LoadAction::class.java, handler?.javaClass)

    }

}