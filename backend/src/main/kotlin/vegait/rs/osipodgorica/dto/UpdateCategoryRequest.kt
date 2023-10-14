package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

data class UpdateCategoryRequest(
    var name: String,
    val thumbnail: MultipartFile? = null,
)
