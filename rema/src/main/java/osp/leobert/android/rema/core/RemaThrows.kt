package osp.leobert.android.rema.core

/**
 * <p><b>Package:</b> osp.leobert.android.rema.core </p>
 * <p><b>Classname:</b> RemaThrows </p>
 * Created by leobert on 2021/5/19.
 */
object RemaThrows {
    abstract class RemaThrowable(
        message: String? = null,
        cause: Throwable? = null,
    ) : Throwable(message, cause)

    abstract class RemaException(
        message: String? = null,
        cause: Throwable? = null
    ) : Exception(message, cause)

    class EmptyCallback : RemaThrowable()

    fun callbackEmpty() = EmptyCallback()


    class MissingModelActionRegistryException(message: String?) : RemaException(message)

    fun missingRegisterModelAction(modelClass: Class<*>, action: Action<*, *>): RemaException {
        return MissingModelActionRegistryException("missing register modelActionHandler for model-class:${modelClass.name},action:$action")
    }

    class ErrorModelActionRegistryException(message: String?) : RemaException(message)

    fun errorRegisterModelAction(info: String): RemaException {
        return ErrorModelActionRegistryException("error type of ActionHandler registered :$info")
    }
}