package vegait.rs.osipodgorica.exceptions.converters

import org.springframework.http.ResponseEntity
import vegait.rs.osipodgorica.dto.ApiErrorResponse
import java.lang.reflect.ParameterizedType

abstract class ExceptionConverter<T : Exception> {

	protected var persistentClass: Class<T>

	init {
		@Suppress("UNCHECKED_CAST")
		this.persistentClass = (javaClass.genericSuperclass as ParameterizedType)
			.actualTypeArguments[0] as Class<T>
	}

	open fun canConvertException(exception: Exception): Boolean {
		return exception.javaClass == persistentClass
	}

	abstract fun convert(exception: T): ResponseEntity<ApiErrorResponse>
}
