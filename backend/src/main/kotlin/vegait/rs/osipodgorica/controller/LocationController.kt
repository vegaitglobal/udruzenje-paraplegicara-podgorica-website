package vegait.rs.osipodgorica.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import vegait.rs.osipodgorica.dto.CreateLocationRequest
import vegait.rs.osipodgorica.dto.UpdateLocationRequest
import vegait.rs.osipodgorica.model.Location
import vegait.rs.osipodgorica.service.LocationService

@RestController
@RequestMapping("/api/v1/locations")
class LocationController(
    val service: LocationService,
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun store(@ModelAttribute request: CreateLocationRequest): Location {
        return service.store(request)
    }

    @GetMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable slug: String): Location {
        return service.get(slug)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun index(
        @RequestParam(name = "categoryId", required = false) categoryId: Long?,
        @RequestParam(name = "cityId", required = false) cityId: Long?,
        @RequestParam(name = "featureIds", required = false) featureIds: List<Long>?,
        @RequestParam(name = "name", required = false) name: String?,
    ): List<Location> {
        return service.index(categoryId, cityId, featureIds, name)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return service.delete(id)
    }

    @PutMapping("/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun update(@ModelAttribute request: UpdateLocationRequest, @PathVariable id: Long): Location {
        return service.update(request, id)
    }

    @DeleteMapping("/{id}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteImage(@PathVariable("id") locId: Long, @PathVariable("imageId") imageId: Long) {
        service.deleteImg(locId, imageId);
    }
}
