package vegait.rs.osipodgorica.dto

import java.time.LocalDate

data class UpdateArchiveRequest(
    val name: String? = null,
    val description: String? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
)
