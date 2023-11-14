package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.Location
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.core.io.Resource
import org.springframework.core.io.ClassPathResource
import vegait.rs.osipodgorica.OsipodgoricaApplication
import vegait.rs.osipodgorica.dto.ExternalLocationDto
import vegait.rs.osipodgorica.dto.LocationCategoryDto
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.model.City
import vegait.rs.osipodgorica.repository.CategoryRepository
import vegait.rs.osipodgorica.repository.CityRepository
import vegait.rs.osipodgorica.repository.LocationRepository

@Component
@Order(3)
class OldDataSeeder(
    val cityRepository: CityRepository,
    val categoryRepository: CategoryRepository,
    val locationRepository: LocationRepository,
    val objectMapper: ObjectMapper
) : CommandLineRunner {
    companion object {
        private val logger = LoggerFactory.getLogger(OsipodgoricaApplication::class.java)
        private val categoryMap = mapOf(
            "Objekti državnih organa" to listOf("Objekti državnih organa"),
            "Lokalna samouprava" to listOf("Lokalna samouprava"),
            "Zdravstvo" to listOf("Zdravstvo"),
            "Obrazovanje" to listOf("Obrazovnje"), // Corrected value from the logs
            "Kultura" to listOf("Kultura"),
            "Otvoreni i zatvoreni sportski i rekreativni objekti" to listOf("Otvoreni i zatvoreni sportski i rekreativni objekti"),
            "Saobraćajni terminali" to emptyList<String>(),
            "Pošte" to listOf("Pošte"),
            "Banke" to listOf("Banke"),
            "Trgovački objekti" to listOf("Trgovine"), // Assumed value from the logs
            "Turistički objekti" to listOf("Turistički objekti"),
            "Ugostiteljski objekti" to listOf("Ugostiteljstvo"), // Assumed value from the logs
            "Vjerski objekti" to listOf("Vjerski objekti"),
            "Telekomunikacije" to listOf("Telekomunikacije"),
            "Zabava" to emptyList<String>(),
            "Javne površine" to listOf("Javne površine"),
            "Apoteke" to listOf("Apoteke"),
            "Ostali" to listOf("Ostalo") // Assumed value from the logs
        )
    }

    override fun run(vararg args: String?) {
        val locationsCategory = locationCategory()
        insertLocations(locationsCategory)
    }

    private fun locationCategory(): List<LocationCategoryDto> {
        val resource: Resource = ClassPathResource("old_data/location-categories-trimmed.json")
        val jsonData = String(resource.inputStream.readBytes())
        return objectMapper.readValue<List<LocationCategoryDto>>(jsonData)
    }

    private fun insertLocations(locationsCategory: List<LocationCategoryDto>) {
        val resource: Resource = ClassPathResource("old_data/locations_with_related_data.json")
        val jsonData = String(resource.inputStream.readBytes())
        val externalLocations: List<ExternalLocationDto> = objectMapper.readValue(jsonData)
        val distinctAccessibilityFeatures = externalLocations.asSequence()
            .map { it.accessibilityTags?.split(",")?.map(String::trim).orEmpty() }
            .flatten()
            .filter { it.isNotBlank() }
            .distinct()
            .toList();

        distinctAccessibilityFeatures.forEach{logger.info(it)}

        val locations = externalLocations.map { externalLocation ->
            //city
            val city: City = cityRepository.findByName(externalLocation.city)
                .orElse(cityRepository.findByName("Nepoznat").orElseThrow())
            //category
            val matchedLocationCategory = locationsCategory.first { loc -> loc.locationId == externalLocation.postId }
            val category: Category = findCategoryByKeyValue(matchedLocationCategory.categoryName)

            val location = Location(
                name = externalLocation.postTitle ?: "No title",
                description = externalLocation.postContent ?: "No content",
                category = category,
                accessibilityFeatures = hashSetOf(),
                latitude = externalLocation.locationLatitude,
                longitude = externalLocation.locationLongitude,
                address = externalLocation.address,
                postalNumber = externalLocation.zipCode?.toLongOrNull() ?: 0,
                city = city,
                slug = externalLocation.postSlugName ?: "no-slug",
                email = externalLocation.email ?: "no email",
                phone = "no phone",
                thumbnailUrl = externalLocation.companyImageUrl ?: "no image"
            )
            location
        }
        locationRepository.saveAll(locations)
    }

    fun findCategoryByKeyValue(value: String): Category {
        // Find the key from the value
        val categoryKey = categoryMap.entries
            .firstOrNull { it.value.contains(value) }
            ?.key

        // If a corresponding key is found, query the repository using that key
        return categoryRepository.findByName(categoryKey ?: "Ostali")
    }
}
