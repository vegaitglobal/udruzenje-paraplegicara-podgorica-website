package vegait.rs.osipodgorica.dto

data class CreateArchiveRequest(
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
)
