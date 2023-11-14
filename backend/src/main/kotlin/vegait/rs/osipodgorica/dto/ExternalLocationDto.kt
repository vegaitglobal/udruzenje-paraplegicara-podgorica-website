package vegait.rs.osipodgorica.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

open class ExternalLocationDto (
    var postId: Long?,
    var postTitle: String?,
    var postContent: String?,
    var postSlugName: String?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var postCreationDatetimeUtc: LocalDateTime = LocalDateTime.now(),
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var postModifiedDatetimeUtc: LocalDateTime = LocalDateTime.now(),
    var address: String?,
    var locationLatitude: Double,
    var locationLongitude: Double,
    var zipCode: String?,
    var city: String,
    var country: String?,
    var webSite: String?,
    var email: String?,
    var companyImageUrl: String?,
    var accessibilityTags: String?
)