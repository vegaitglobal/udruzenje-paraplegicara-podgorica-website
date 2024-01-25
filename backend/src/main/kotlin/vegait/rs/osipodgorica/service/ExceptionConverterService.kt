package vegait.rs.osipodgorica.service

import lombok.extern.slf4j.Slf4j
import vegait.rs.osipodgorica.exceptions.converters.ExceptionConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import vegait.rs.osipodgorica.dto.ApiErrorResponse

@Service
@Slf4j
class ExceptionConverterService<T : Exception> @Autowired constructor(private val exceptionConverters: List<ExceptionConverter<T>>) {

	private val log: Logger = LoggerFactory.getLogger(ExceptionConverterService::class.java)

	fun convert(ex: T): ResponseEntity<ApiErrorResponse> {
		val matchingConverter = exceptionConverters
			.stream()
			.filter { converter -> converter.canConvertException(ex) }
			.findFirst()

		if (matchingConverter.isEmpty) {
			log.warn(
				"No exception converter could have been found for the following exception " +
						"${ex.javaClass} with message: ${ex.message}"
			)
			throw ex
		}

		return matchingConverter.get().convert(ex)
	}
}