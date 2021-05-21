package osp.leobert.android.rema.cache

/**
 * <p><b>Package:</b> osp.leobert.android.rema.cache </p>
 * <p><b>Classname:</b> CacheStrategy </p>
 * Created by leobert on 2021/5/21.
 */
enum class CacheStrategy(val info:String) {
    NONE("none"),
    MEMORY("memory"),
    ONLY_UPDATE_MEMORY("only_update_memory")

}