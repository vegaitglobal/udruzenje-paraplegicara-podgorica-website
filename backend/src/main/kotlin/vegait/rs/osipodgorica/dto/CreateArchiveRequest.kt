package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class CreateArchiveRequest(
    val name: String,
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val file: MultipartFile,
)
