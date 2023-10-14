package vegait.rs.osipodgorica.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import vegait.rs.osipodgorica.dto.CreateAccessibilityFeature
import vegait.rs.osipodgorica.dto.UpdateAccessibilityFeature
import vegait.rs.osipodgorica.model.AccessibilityFeature
import vegait.rs.osipodgorica.service.AccessibilityFeatureService

@RestController
@RequestMapping("/api/v1/accessibility-features")
class AccessibilityFeatureController(private val service: AccessibilityFeatureService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun store(@ModelAttribute request: CreateAccessibilityFeature): AccessibilityFeature {
        return service.store(request)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: Long): AccessibilityFeature {
        return service.get(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun index(): List<AccessibilityFeature> {
        return service.index()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@ModelAttribute request: UpdateAccessibilityFeature, @PathVariable id: Long): AccessibilityFeature {
        return service.update(request, id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return service.delete(id)
    }
}