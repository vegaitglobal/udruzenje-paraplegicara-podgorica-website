package vegait.rs.osipodgorica.service

import com.blazebit.persistence.CriteriaBuilderFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vegait.rs.osipodgorica.dto.CreateLocationRequest
import vegait.rs.osipodgorica.dto.UpdateLocationRequest
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.model.Location
import vegait.rs.osipodgorica.model.LocationImage
import vegait.rs.osipodgorica.repository.*
import vegait.rs.osipodgorica.utils.LocationQueryBuilder

@Service
@Transactional
class LocationService(
    val repository: LocationRepository,
    val categoryRepo: CategoryRepository,
    val cityRepository: CityRepository,
    val featureRepo: AccessibilityFeatureRepository,
    val entityManager: EntityManager,
    val criteriaBuilderFactory: CriteriaBuilderFactory,
    val imageService: LocationImageService,
    val uploadService: ImageUploadService,
    val imageRepository: LocationImageRepository
) {

    fun store(request: CreateLocationRequest): Location {
        val category: Category = categoryRepo.findById(request.categoryId).orElseThrow()
        val features = featureRepo.findAllByIdIn(request.accessibilityFeatureIds)
        val city = cityRepository.findById(request.cityId).orElseThrow()

        var location = Location(
            name = request.name,
            description = request.description,
            category = category,
            accessibilityFeatures = features,
            latitude = request.latitude,
            longitude = request.longitude,
            address = request.address ?: "",
            postalNumber = request.postalNumber ?: 81000,
            city = city,
        )

        location = repository.save(location);

        val imageSet: MutableList<LocationImage> = mutableListOf()
        for (image in request.images!!) {
            // store images
            val url = uploadService.store(image, "locations/" + location.id)
            imageSet.add(LocationImage(relativeUrl = url))
        }

        // update with info about images
        location.images = imageSet
        location.thumbnailUrl = uploadService.store(request.thumbnail, "locations/" + location.id)

        return repository.save(location)
    }

    fun get(id: Long): Location {
        return repository.findById(id).orElseThrow()
    }

    fun index(categoryId: Long?, cityId: Long?, featureIds: List<Long>?, name: String?): List<Location> {
        return LocationQueryBuilder(criteriaBuilderFactory, entityManager)
            .accessibilityFeatures(featureIds)
            .category(categoryId)
            .city(cityId)
            .name(name)
            .build()
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

    fun update(request: UpdateLocationRequest, id: Long): Location {
        val existingLocation = repository.findById(id).orElseThrow()
        val category: Category = categoryRepo.findById(request.categoryId).orElseThrow()
        val features = featureRepo.findAllByIdIn(request.accessibilityFeatureIds)

        existingLocation.name = request.name
        existingLocation.description = request.description
        existingLocation.category = category
        existingLocation.latitude = request.latitude
        existingLocation.longitude = request.longitude
        existingLocation.accessibilityFeatures = features
        if (request.address != null) {
            existingLocation.address = request.address
        }
        if (request.postalNumber != null) {
            existingLocation.postalNumber = request.postalNumber
        }

        return repository.save(existingLocation)
    }
}
