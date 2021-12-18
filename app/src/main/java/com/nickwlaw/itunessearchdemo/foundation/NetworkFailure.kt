package com.nickwlaw.itunessearchdemo.foundation

sealed class NetworkFailure(message: String? = null, cause: Throwable? = null) :
    Failure(message, cause) {
    class Unknown(message: String? = null, cause: Throwable? = null) :
        NetworkFailure(message = message, cause = cause)

    class NetworkConnection(cause: Throwable) : NetworkFailure(cause = cause)
    class ConnectionTimeout(cause: Throwable) : NetworkFailure(cause = cause)
    class ServerError(
        val code: Int,
        message: String?,
        val errors: List<String>? = null
    ) : NetworkFailure(message) {
        val error = errors?.firstOrNull()
    }

    object UnexpectedEmptyBody :
        NetworkFailure("Unexpected empty body returned in network response")
}
