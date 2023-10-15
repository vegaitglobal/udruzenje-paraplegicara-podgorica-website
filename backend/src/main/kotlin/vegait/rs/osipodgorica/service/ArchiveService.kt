package vegait.rs.osipodgorica.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import vegait.rs.osipodgorica.dto.CreateArchiveRequest
import vegait.rs.osipodgorica.model.Archive
import vegait.rs.osipodgorica.repository.ArchiveRepository

private const val ARCHIVE_PATH = "/uploads/archive/%s"

@Service
@Transactional
class ArchiveService(
    val archiveRepository: ArchiveRepository,
    val fileUploadService: ImageUploadService,
) {

    fun store(request: CreateArchiveRequest): Archive =
        Archive(
            description = request.description,
            name = request.name,
            startDate = request.startDate,
            endDate = request.endDate,
            path = ARCHIVE_PATH.format(request.name),
        )

    fun delete(id: Long) {
        archiveRepository.findById(id).get().let { archive ->
            fileUploadService.deleteFile(archive.path).also {
                archiveRepository.deleteById(id)
            }
        }
    }
}
