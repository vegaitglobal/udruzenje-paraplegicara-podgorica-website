package vegait.rs.osipodgorica.dto

data class UpdateArchiveRequest(
    val name: String? = null,
    val description: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
)
