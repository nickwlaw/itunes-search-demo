package com.nickwlaw.itunessearchdemo.foundation

/**
 * Base Class for handling errors/failures/exceptions.
 * To be used by Features, Services etc to define sealed errors which they may return in case
 * of failing
 */
open class Failure(message: String? = null, cause: Throwable? = null) : Throwable(message, cause)
