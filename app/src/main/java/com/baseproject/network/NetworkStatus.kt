package com.baseproject.network

sealed class NetworkStatus

class SuccessResponse<T>(val data: T) : NetworkStatus()

sealed class Errors: NetworkStatus()

class TimeOutError(val message: String = "Time out error"): Errors()
class NoInternetError(val message: String = "No internet error"): Errors()
class BadRequestError(val message: String = "Bad request error"): Errors()
class InternalServerError(val message: String = "Internal server error"): Errors()
class ParseError(val message: String = "Parse error"): Errors()
