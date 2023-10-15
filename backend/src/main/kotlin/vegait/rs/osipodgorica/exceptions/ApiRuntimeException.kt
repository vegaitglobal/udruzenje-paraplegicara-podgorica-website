package vegait.rs.osipodgorica.exceptions

import org.springframework.http.HttpStatus

open class ApiRuntimeException : RuntimeException {

	val status: HttpStatus?
	override var message: String? = null

	constructor(message: String, status: HttpStatus) {
		this.status = status
		this.message = message
	}

	constructor(message: String) {
		this.status = null
		this.message = message
	}
}