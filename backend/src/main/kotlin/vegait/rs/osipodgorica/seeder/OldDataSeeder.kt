package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.Location
import vegait.rs.osipodgorica.repository.LocationRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.ClassPathResource
import vegait.rs.osipodgorica.OsipodgoricaApplication
import vegait.rs.osipodgorica.dto.ExternalLocationDto
import vegait.rs.osipodgorica.dto.LocationCategoryDto

@Component
class OldDataSeeder(
    val modelMapper: ModelMapper,
    val objectMapper: ObjectMapper
) : CommandLineRunner {
    companion object {
        private val logger = LoggerFactory.getLogger(OsipodgoricaApplication::class.java)
    }

    override fun run(vararg args: String?) {
        val locationsCategory = locationCategory()
        insertLocations(locationsCategory)
    }

    private fun locationCategory(): List<LocationCategoryDto> {
        val resource: Resource = ClassPathResource("old_data/location-categories-trimmed.json")
        val jsonData = String(resource.inputStream.readBytes())
        val locationWithCategory: List<LocationCategoryDto> = objectMapper.readValue(jsonData);
        return locationWithCategory
    }

    private fun insertLocations(locationsCategory: List<LocationCategoryDto>) {
        //for each data serialized in file create location and add it to arrayList
        val resource: Resource = ClassPathResource("old_data/locations_with_related_data.json")
        val jsonData = String(resource.inputStream.readBytes())
        val externalLocations: List<ExternalLocationDto> = objectMapper.readValue(jsonData)
        val locations = externalLocations.map { externalLocation ->
            val location = modelMapper.map(externalLocation, Location::class.java)
            println(location.id)
            println(location.city)
        }
//        val locations = externalLocations.map { externalLocation ->
//            val location = locationMapper.map(externalLocation, Location::class.java)
//            logger.info(location.toString())
//            location
//        }
//        locationRepository.saveAll(locations)
    }
}
