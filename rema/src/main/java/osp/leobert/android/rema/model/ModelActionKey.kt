package osp.leobert.android.rema.model

import osp.leobert.android.rema.core.Action
import osp.leobert.android.rema.core.Key
import java.security.MessageDigest

/**
 * <p><b>Package:</b> osp.leobert.android.rema.model </p>
 * <p><b>Classname:</b> ModelActionKey </p>
 * Created by leobert on 2021/5/21.
 */
class ModelActionKey<Model, Result>(
    private val model: Model,
    private val action: Action<Model, Result>
) : Key {

    // if use Key.Generator.keyToken,key may be cached, model has leak risk
    private val keyToken: String by lazy {
        Key.Generator.calculateKeyToken(this)
    }

    override fun updateKey(messageDigest: MessageDigest) {
        action.updateKey(model, messageDigest)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModelActionKey<*, *>

        if (keyToken != other.keyToken) return false

        return true
    }

    override fun hashCode(): Int {
        return keyToken.hashCode()
    }


}