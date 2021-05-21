package osp.leobert.android.rema.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import osp.leobert.android.rema.Rema
import osp.leobert.android.rema.RemaTest.*
import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.ActionHandler
import osp.leobert.android.rema.core.RemaThrows
import osp.leobert.android.rema.core.Request
import osp.leobert.android.rema.factory.ModelActionHandlerFactory


/**
 * <p><b>Package:</b> osp.leobert.android.rema.model </p>
 * <p><b>Classname:</b> ModelActionRegistryTest </p>
 * Created by leobert on 2021/5/19.
 */
class ModelActionRegistryTest {

    var target: ModelActionRegistry = ModelActionRegistry(Rema())


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

        target.append(
            M1::class.java,
            ActionM1R,
            object : ModelActionHandlerFactory<M1, R> {
                override fun create(
                    modelClass: Class<M1>,
                    resultClass: Class<R>,
                    action: Action<M1, R>,
                    callback: ActionHandler.Callback<M1, R>
                ): ModelActionHandler<M1, R> {
                    return object : ModelActionHandler<M1, R>(callback) {
                        override fun cancelAction() {
                        }

                        override fun handle(model: M1, request: Request<M1, R>) {
                        }
                    }
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

    @Test
    fun test_Missing_MR() {
        try {

            target.getActionHandler(
                M1::class.java,
                R1::class.java,
                object : Action<M1, R1>() {}
            )
            assertNotNull(null) //must goto exception
        } catch (e: Exception) {
            e.printStackTrace()
            assertEquals(RemaThrows.MissingModelActionRegistryException::class.java, e.javaClass)
        }
    }

    @Test
    fun test_Error_M1R() {
        //理论上无法进行错误注册，泛型约束为不变
//        try {
//
//            target.getActionHandler(
//                M1::class.java,
//                R::class.java,
//                ActionM1R
//            )
//            assertNotNull(null) //must goto exception
//        } catch (e: Exception) {
//            e.printStackTrace()
//            assertEquals(RemaThrows.MissingModelActionRegistryException::class.java, e.javaClass)
//        }
    }

}