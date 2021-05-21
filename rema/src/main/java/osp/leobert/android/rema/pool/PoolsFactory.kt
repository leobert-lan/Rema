package osp.leobert.android.rema.pool

import androidx.core.util.Pools
import androidx.core.util.Pools.SimplePool
import androidx.core.util.Pools.SynchronizedPool
import osp.leobert.android.rema.takeIfInstance

/**
 * <p><b>Package:</b> osp.leobert.android.rema.pool </p>
 * <p><b>Classname:</b> PoolsFactory </p>
 * Created by leobert on 2021/5/21.
 */
@Suppress("WeakerAccess")
object PoolsFactory {
    private const val DEFAULT_POOL_SIZE = 20

    private val EMPTY_RESETTER: Resetter<Any> = object : Resetter<Any> {

        override fun reset(target: Any) {
            //do nothing
        }
    }


    interface Factory<T> {
        fun create(): T
    }


    interface Resetter<T> {
        fun reset(target: T)
    }

    private class FactoryPool<T> constructor(
        private val pool: Pools.Pool<T>,
        private val factory: Factory<T>,
        private val resetter: Resetter<T>
    ) : Pools.Pool<T> {
        override fun acquire(): T? {
            val result = pool.acquire() ?: factory.create()
            result.takeIfInstance<PoolItem>()?.verifier?.setRecycled(false)

            return result
        }

        override fun release(instance: T): Boolean {
            instance.takeIfInstance<PoolItem>()?.verifier?.setRecycled(true)
            return instance?.run {
                resetter.reset(this)
                pool.release(this)
            } ?: true /*always return true when release null*/
        }
    }


    @JvmStatic
    fun <T : PoolItem?> simple(size: Int, factory: Factory<T>): Pools.Pool<T> {
        return build(pool = SimplePool(size), factory = factory)
    }

    @JvmStatic
    fun <T : PoolItem?> threadSafe(size: Int, factory: Factory<T>): Pools.Pool<T> {
        return build(pool = SynchronizedPool(size), factory = factory)
    }

    @JvmStatic
    fun <T> threadSafeList(): Pools.Pool<MutableList<T>?> {
        return threadSafeList(size = DEFAULT_POOL_SIZE)
    }

    @JvmStatic
    fun <T> threadSafeList(size: Int): Pools.Pool<MutableList<T>?> {
        return build(
            pool = SynchronizedPool(size),
            factory = object : Factory<MutableList<T>?> {
                override fun create(): MutableList<T> = ArrayList()
            },
            resetter = object : Resetter<MutableList<T>?> {
                override fun reset(target: MutableList<T>?) {
                    target?.clear()
                }
            }
        )
    }

    private fun <T : PoolItem?> build(pool: Pools.Pool<T>, factory: Factory<T>): Pools.Pool<T> {
        return build(
            pool = pool,
            factory = factory,
            resetter = EMPTY_RESETTER.takeIfInstance<Resetter<T>>() ?: object : Resetter<T> {
                override fun reset(target: T) {
                    //do nothing
                }

            })
    }

    private fun <T> build(
        pool: Pools.Pool<T>,
        factory: Factory<T>,
        resetter: Resetter<T>
    ): Pools.Pool<T> {
        return FactoryPool(pool, factory, resetter)
    }
}