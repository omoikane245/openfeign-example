package com.example.openfeignexample.exception

class EmptyMessageException: RuntimeException {
	constructor(message: String)

	constructor(message: String, throwable: Throwable)
}
