package osp.leobert.android.rema.pool

/**
 * <p><b>Package:</b> osp.leobert.android.rema.pool </p>
 * <p><b>Classname:</b> StateVerifier </p>
 * Created by leobert on 2021/5/21.
 *
 * StateVerifier, from Glide
 * Copyright 2014 Google, Inc. All rights reserved.
 */
sealed class StateVerifier {
    companion object {
        fun newInstance(): StateVerifier {
            return DefaultImpl()
        }
    }

    /**
     * Throws an exception if we believe our object is recycled and inactive (i.e. is currently in an
     * object pool).
     */
    abstract fun throwIfRecycled()

    /** Sets whether or not our object is recycled.  */
    abstract fun setRecycled(isRecycled: Boolean)

    private class DefaultImpl : StateVerifier() {
        @Volatile
        private var isReleased = false
        override fun throwIfRecycled() {
            check(!isReleased) { "Already released" }
        }

        override fun setRecycled(isRecycled: Boolean) {
            isReleased = isRecycled
        }

    }
}