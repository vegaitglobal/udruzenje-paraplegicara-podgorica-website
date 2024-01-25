package vegait.rs.osipodgorica.configuration

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import vegait.rs.osipodgorica.dto.ApiErrorResponse
import vegait.rs.osipodgorica.service.ExceptionConverterService

@ControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler(val exceptionConverterService: ExceptionConverterService<Exception>) {

	@ExceptionHandler(Exception::class)
	fun handleException(e: Exception): ResponseEntity<ApiErrorResponse> {
		return exceptionConverterService.convert(e)
	}
}