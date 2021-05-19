package osp.leobert.android.rema

class Rema {
}

inline fun safe(lambda: () -> Unit) {
    try {
        lambda.invoke()
    } catch (e: Exception) {
        // TODO: 2021/5/19 log
    }
}

inline fun <reified R> Any?.takeIfInstance(): R? {
    if (this is R) return this
    return null
}