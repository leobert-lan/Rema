package osp.leobert.android.rema

/**
 * <p><b>Package:</b> osp.leobert.android.rema </p>
 * <p><b>Classname:</b> RemaTest </p>
 * Created by leobert on 2021/5/19.
 */
class RemaTest {
}

//inline fun <reified R> Any?.takeIfInstance(): R? {
//    if (this is R) return this
//    return null
//}

inline fun <reified R> Class<*>.takeIfAssignableFrom(): Class<R>? {
    return if (R::class.java.isAssignableFrom(this)) R::class.java
    else null
}