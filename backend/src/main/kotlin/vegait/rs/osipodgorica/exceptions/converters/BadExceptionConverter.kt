package vegait.rs.osipodgorica.exceptions.converters

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.dto.ApiErrorResponse
import vegait.rs.osipodgorica.exceptions.BadRequestException
import vegait.rs.osipodgorica.service.ExceptionConverterService
import vegait.rs.osipodgorica.utils.ErrorMessageBuilder

@Component
@Slf4j
class BadRequestConverter @Autowired constructor(private val errorMessageBuilder: ErrorMessageBuilder) : ExceptionConverter<BadRequestException>() {

	private val log: Logger = LoggerFactory.getLogger(ExceptionConverterService::class.java)

	override fun convert(exception: BadRequestException): ResponseEntity<ApiErrorResponse> {
		log.error("A bad request error has occurred: {}", exception.message)
		return errorMessageBuilder.buildErrorResponse(exception, HttpStatus.BAD_REQUEST)
	}
}
