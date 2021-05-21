package osp.leobert.android.rema.core

import java.security.MessageDigest

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> Action </p>
 * Created by leobert on 2021/5/18.
 */
abstract class Action<Model, Result> : Key.KeyRefer<Model> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return javaClass.name
    }

    override fun updateKey(refer: Model, messageDigest: MessageDigest) {
        messageDigest.update((refer?.toString() ?: "").toByteArray(Key.CHARSET))
    }

}