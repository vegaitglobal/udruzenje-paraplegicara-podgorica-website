package vegait.rs.osipodgorica.utils

import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.dto.ApiErrorResponse
import vegait.rs.osipodgorica.exceptions.ApiRuntimeException
import java.time.LocalDateTime

@Component
class ErrorMessageBuilder {

	@Value("\${osipodgorica.error-response.include.stack-trace:false}")
	private var includeStackTrace: Boolean = false

	@Value("\${osipodgorica.error-response.include.original-error-message:false}")
	private var includeOriginalErrorMessage: Boolean = false

	fun buildErrorResponse(
		exception: Exception,
		httpStatus: HttpStatus,
		timestamp: LocalDateTime? = LocalDateTime.now()
	): ResponseEntity<ApiErrorResponse> {
		// In staging and prod we don't want to show Java exception messages
		var message = exception.message
		if (exception !is ApiRuntimeException && !includeOriginalErrorMessage) {
			message = exception.message
		}

		val errorResponse = ApiErrorResponse(httpStatus.value(), message, timestamp)

		if (includeStackTrace) {
			errorResponse.stackTrace = ExceptionUtils.getStackTrace(exception)
		}

		return ResponseEntity.status(httpStatus).body(errorResponse)
	}
}