package vegait.rs.osipodgorica.exceptions.converters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import vegait.rs.osipodgorica.dto.ApiErrorResponse
import vegait.rs.osipodgorica.service.ExceptionConverterService
import vegait.rs.osipodgorica.utils.ErrorMessageBuilder

@Component
class MethodArgumentNotValidExceptionConverter @Autowired constructor(
	private val errorMessageBuilder: ErrorMessageBuilder
) : ExceptionConverter<MethodArgumentNotValidException>() {

	private val log: Logger = LoggerFactory.getLogger(ExceptionConverterService::class.java)

	override fun convert(exception: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
		log.error("A method argument not valid exception has occurred: {}", exception.message)
		return errorMessageBuilder.buildErrorResponse(exception, HttpStatus.BAD_REQUEST)
	}
}