package vegait.rs.osipodgorica.controller

import jakarta.transaction.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vegait.rs.osipodgorica.service.ArchiveService

@RestController
@RequestMapping("/api/v1/archive")
@Transactional
class ArchiveController(
    private val archiveService: ArchiveService,
) {

}
