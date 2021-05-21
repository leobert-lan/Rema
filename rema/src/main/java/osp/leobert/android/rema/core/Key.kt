package osp.leobert.android.rema.core

import androidx.collection.LruCache
import androidx.core.util.Pools
import osp.leobert.android.rema.Util
import osp.leobert.android.rema.pool.PoolItem
import osp.leobert.android.rema.pool.PoolsFactory
import osp.leobert.android.rema.pool.StateVerifier
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> Key </p>
 * Created by leobert on 2021/5/21.
 */
interface Key {
    companion object {
        var STRING_CHARSET_NAME = "UTF-8"
        var CHARSET = Charset.forName(STRING_CHARSET_NAME)
    }

    interface KeyRefer<R> {
        fun updateKey(refer: R, messageDigest: MessageDigest)
    }


    fun updateKey(messageDigest: MessageDigest)

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int


    object Generator {
        private val loadIdToSafeHash: LruCache<Key, String> = LruCache(1000)
        private val digestPool: Pools.Pool<DigestContainer> = PoolsFactory.threadSafe(
            size = 10,
            factory = object : PoolsFactory.Factory<DigestContainer> {
                override fun create(): DigestContainer {
                    return try {
                        DigestContainer(MessageDigest.getInstance("SHA-256"))
                    } catch (e: NoSuchAlgorithmException) {
                        throw RuntimeException(e)
                    }
                }

            })

        fun keyToken(key: Key): String {
            var safeKey: String?
            synchronized(loadIdToSafeHash) {
                safeKey = loadIdToSafeHash[key]
            }
            if (safeKey == null) {
                safeKey = calculateKeyToken(key)
            }
            synchronized(loadIdToSafeHash) {
                loadIdToSafeHash.put(key, safeKey!!)
            }
            return safeKey!!
        }

        fun calculateKeyToken(key: Key): String = checkNotNull(calculateHexStringDigest(key))

        private fun calculateHexStringDigest(key: Key): String? {
            val container: DigestContainer = checkNotNull(digestPool.acquire())
            return try {
                key.updateKey(container.messageDigest)
                // calling digest() will automatically reset()
                Util.sha256BytesToHex(container.messageDigest.digest())
            } finally {
                digestPool.release(container)
            }
        }
    }

    private class DigestContainer(val messageDigest: MessageDigest) : PoolItem {
        override val verifier: StateVerifier = StateVerifier.newInstance()
    }
}