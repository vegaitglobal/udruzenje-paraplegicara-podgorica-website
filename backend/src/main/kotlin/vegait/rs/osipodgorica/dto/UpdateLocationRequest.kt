package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

data class UpdateLocationRequest(
    val name: String,
    val slug: String,
    val description: String,
    val categoryId: Long,
    val accessibilityFeatureIds: List<Long>,
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val postalNumber: Long?,
    val thumbnail: MultipartFile?,
    val images: List<MultipartFile>?
)
