package vegait.rs.osipodgorica.dto

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiErrorResponse(
	var status: Int? = null,
	var message: String? = null,
	var timestamp: LocalDateTime? = null,
	var stackTrace: String? = null,
)