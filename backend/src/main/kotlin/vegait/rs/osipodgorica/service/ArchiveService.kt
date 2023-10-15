package vegait.rs.osipodgorica.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import vegait.rs.osipodgorica.dto.CreateArchiveRequest
import vegait.rs.osipodgorica.dto.UpdateArchiveRequest
import vegait.rs.osipodgorica.model.Archive
import vegait.rs.osipodgorica.repository.ArchiveRepository

private const val ARCHIVE_FOLDER_NAME = "archive/"

@Service
@Transactional
class ArchiveService(
    val archiveRepository: ArchiveRepository,
    val fileUploadService: ImageUploadService,
) {

    fun store(request: CreateArchiveRequest): Archive =
        fileUploadService.store(request.file, ARCHIVE_FOLDER_NAME).let { relativeUrl ->
            archiveRepository.save(
                Archive(
                    description = request.description,
                    name = request.name,
                    startDate = request.startDate,
                    endDate = request.endDate,
                    relativeUrl = relativeUrl,
                ),
            )
        }

    fun update(request: UpdateArchiveRequest, id: Long): Archive = archiveRepository.findById(id).get().let { oldArchive ->
        Archive(
            id = id,
            name = request.name ?: oldArchive.name,
            description = request.description ?: oldArchive.description,
            startDate = request.startDate ?: oldArchive.startDate,
            endDate = request.endDate ?: oldArchive.endDate,
            relativeUrl = oldArchive.relativeUrl,
        )
    }.let { newArchive ->
        archiveRepository.save(newArchive)
    }

    fun delete(id: Long) {
        archiveRepository.findById(id).get().let { archive ->
            fileUploadService.deleteFile(archive.relativeUrl).also {
                archiveRepository.deleteById(id)
            }
        }
    }

    fun get(id: Long): Archive = archiveRepository.findById(id).get()

    fun index(): List<Archive> = archiveRepository.findAll()
}
