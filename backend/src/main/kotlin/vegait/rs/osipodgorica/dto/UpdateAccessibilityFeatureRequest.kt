package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

data class UpdateAccessibilityFeatureRequest(
    val name: String,
    val thumbnail: MultipartFile?
)
