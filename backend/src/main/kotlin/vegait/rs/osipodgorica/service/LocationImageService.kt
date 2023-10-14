package vegait.rs.osipodgorica.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import vegait.rs.osipodgorica.model.LocationImage
import vegait.rs.osipodgorica.repository.LocationImageRepository

@Transactional
@Service
class LocationImageService(
    val imageRepo: LocationImageRepository,
    val uploadService: ImageUploadService
) {
    fun store(locationImage: LocationImage): LocationImage {
        return imageRepo.save(locationImage)
    }

    fun storeAll(images: List<MultipartFile>?): Set<LocationImage>? {
        if (images == null) {
            return hashSetOf()
        }
        // treba sacuvati image u bazu
        // izvaditi id
        // sacuvati sliku
        // update modela sa url slike

        // ako dodje do greske prilikom cuvanja bilo koje slike treba da se revertuje sve - slike, podaci u bazi i lokacije

        val imageSet: Set<LocationImage> = hashSetOf()

//        for (image in images) {
//            var storedImg: LocationImage = store(LocationImage(relativeUrl = ""))
//            val imgUrl: String = uploadService.store(image, "locations")
//
//            storedImg.relativeUrl = imgUrl
//            storedImg = store(storedImg)
//
//            imageSet.plus(storedImg)
//        }

        return imageSet
    }
}