package vegait.rs.osipodgorica.dto

data class UpdateCategoryRequest(
    var name: String,
    var relativeUrl: String? = null,
)
