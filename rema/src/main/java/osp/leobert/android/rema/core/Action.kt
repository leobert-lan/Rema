package osp.leobert.android.rema.core

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> Action </p>
 * Created by leobert on 2021/5/18.
 */
abstract class Action<Model, Result> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}