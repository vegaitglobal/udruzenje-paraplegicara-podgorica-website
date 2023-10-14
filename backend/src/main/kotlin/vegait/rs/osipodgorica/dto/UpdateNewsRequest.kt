package vegait.rs.osipodgorica.dto

import org.springframework.web.multipart.MultipartFile

class UpdateNewsRequest (
	var title: String,
	var slug: String?,
	var content: String,
	var image: MultipartFile?,
	var tagIds: Set<Long>? = emptySet()
)