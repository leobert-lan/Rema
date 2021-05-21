package osp.leobert.android.rema.core

import osp.leobert.android.rema.model.ModelActionKey

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> Request </p>
 * Created by leobert on 2021/5/18.
 */
class Request<Model, Result>(
    private val job: Job,
    private val model: Model,
    private val action: Action<Model, Result>
) {

    private val modelActionKey: ModelActionKey<Model, Result> = ModelActionKey(model, action)


//    override fun updateKey(messageDigest: MessageDigest) {
//    }
//
//    override fun equals(other: Any?): Boolean {
//    }
//
//    override fun hashCode(): Int {
//    }


}