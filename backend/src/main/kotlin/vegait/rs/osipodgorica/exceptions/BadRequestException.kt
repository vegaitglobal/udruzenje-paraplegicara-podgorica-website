package vegait.rs.osipodgorica.exceptions

import org.springframework.http.HttpStatus

class BadRequestException: ApiRuntimeException {
	constructor(message: String) : super(message)
	constructor(message: String, status: HttpStatus) : super(message, status)
}