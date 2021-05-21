package osp.leobert.android.rema

import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.Request
import osp.leobert.android.rema.model.ModelActionHandler

/**
 * <p><b>Package:</b> osp.leobert.android.rema </p>
 * <p><b>Classname:</b> RemaTest </p>
 * Created by leobert on 2021/5/19.
 */
class RemaTest {

    open class M {

        class LoadAction(callback: Callback<M, R>) : ModelActionHandler<M, R>(
            callback
        ) {
            override fun cancelAction() {
            }

            override fun handle(model: M, request: Request<M, R>) {
            }

        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }


    }

    class M1 : M() {

        var str:String? = null
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false

            other as M1

            if (str != other.str) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + (str?.hashCode() ?: 0)
            return result
        }

    }

    open class R

    class R1 : R()

    object ActionMR : Action<M, R>()

    object ActionM1R : Action<M1, R>()
}

//inline fun <reified R> Any?.takeIfInstance(): R? {
//    if (this is R) return this
//    return null
//}

inline fun <reified R> Class<*>.takeIfAssignableFrom(): Class<R>? {
    return if (R::class.java.isAssignableFrom(this)) R::class.java
    else null
}