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
        private val archiveRepository: ArchiveRepository,
        private val fileUploadService: FileUploadService,
) {

    fun store(request: CreateArchiveRequest): Archive =
        fileUploadService.store(request.file, ARCHIVE_FOLDER_NAME).let { relativeUrl ->
            archiveRepository.save(
                Archive(
                    description = request.description,
                    name = request.name,
                    startDateInstance = request.startDate,
                    endDateInstance = request.endDate,
                    relativeUrl = relativeUrl,
                ),
            )
        }

    fun update(request: UpdateArchiveRequest, id: Long): Archive = archiveRepository.findById(id).get().let { oldArchive ->
        Archive(
            id = id,
            name = request.name ?: oldArchive.name,
            description = request.description ?: oldArchive.description,
            startDateInstance = request.startDate ?: oldArchive.startDateInstance,
            endDateInstance = request.endDate ?: oldArchive.endDateInstance,
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
