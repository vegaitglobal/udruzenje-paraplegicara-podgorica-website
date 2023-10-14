package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

data class CreateAccessibilityFeatureRequest(
    val name: String,
    val thumbnail: MultipartFile
)
