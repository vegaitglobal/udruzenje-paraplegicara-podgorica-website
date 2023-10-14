package vegait.rs.osipodgorica.dto

data class CreateLocationRequest(
    val name: String,
    val slug: String,
    val description: String,
    val categoryId: Long,
    val accessibilityFeatureIds: List<Long>,
    val latitude: Double,
    val longitude: Double,
    val cityId: Long,
    val address: String?,
    val postalNumber: Long?,
)
