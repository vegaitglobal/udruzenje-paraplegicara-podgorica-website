package vegait.rs.osipodgorica.controller

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import vegait.rs.osipodgorica.dto.CreateArchiveRequest
import vegait.rs.osipodgorica.dto.UpdateArchiveRequest
import vegait.rs.osipodgorica.model.Archive
import vegait.rs.osipodgorica.service.ArchiveService

@RestController
@RequestMapping("/api/v1/archive")
@Transactional
class ArchiveController(
    private val archiveService: ArchiveService,
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun store(@ModelAttribute createArchiveRequest: CreateArchiveRequest): Archive =
        archiveService.store(createArchiveRequest)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody updateArchiveRequest: UpdateArchiveRequest, @PathVariable id: Long): Archive =
        archiveService.update(updateArchiveRequest, id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun index(): List<Archive> = archiveService.index()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: Long): Archive = archiveService.get(id)

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(id: Long) {
        archiveService.delete(id)
    }
}
