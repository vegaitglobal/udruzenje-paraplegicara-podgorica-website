package vegait.rs.osipodgorica.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vegait.rs.osipodgorica.dto.CreateAccessibilityFeature
import vegait.rs.osipodgorica.model.AccessibilityFeature
import vegait.rs.osipodgorica.repository.AccessibilityFeatureRepository

@Service
@Transactional
class AccessibilityFeatureService(val featureRepo: AccessibilityFeatureRepository, val uploadService: ImageUploadService) {

    fun store(request: CreateAccessibilityFeature): AccessibilityFeature {
        val feature = featureRepo.save(AccessibilityFeature(name = request.name))

        val imagePath = uploadService.store(request.thumbnail, "features/" + feature.id)
        feature.relativeUrl = imagePath

        return featureRepo.save(feature)
    }

    fun get(id: Long): AccessibilityFeature {
        return featureRepo.findById(id).orElseThrow()
    }

    fun index(): List<AccessibilityFeature> {
        return featureRepo.findAll()
    }
}