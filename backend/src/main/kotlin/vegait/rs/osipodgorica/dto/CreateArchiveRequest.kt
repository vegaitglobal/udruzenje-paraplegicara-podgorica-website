package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

data class CreateArchiveRequest(
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val file: MultipartFile,
)
