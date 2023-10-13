package vegait.rs.osipodgorica.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import vegait.rs.osipodgorica.dto.CreateAccessibilityFeature
import vegait.rs.osipodgorica.model.AccessibilityFeature
import vegait.rs.osipodgorica.service.AccessibilityFeatureService

@RestController
@RequestMapping("/api/v1/accessibility-features")
class AccessibilityFeatureController(private val service: AccessibilityFeatureService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun store(@ModelAttribute request: CreateAccessibilityFeature): AccessibilityFeature {
        return service.store(request)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): AccessibilityFeature {
        return service.get(id)
    }

    @GetMapping
    fun index(): List<AccessibilityFeature> {
        return service.index()
    }
}