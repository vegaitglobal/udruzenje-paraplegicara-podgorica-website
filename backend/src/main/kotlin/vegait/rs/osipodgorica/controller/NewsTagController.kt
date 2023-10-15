package vegait.rs.osipodgorica.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import vegait.rs.osipodgorica.dto.CreateNewsTagRequest
import vegait.rs.osipodgorica.dto.UpdateNewsTagRequest
import vegait.rs.osipodgorica.model.NewsTag
import vegait.rs.osipodgorica.service.NewsTagService

@RestController
@RequestMapping("/api/v1/news-tags")
@Transactional
class NewsTagController (val newsTagService: NewsTagService) {

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	@ResponseStatus(HttpStatus.CREATED)
	fun store(@RequestBody request: CreateNewsTagRequest): NewsTag {
		return newsTagService.store(request)
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun index(): List<NewsTag> {
		return newsTagService.index()
	}

	@GetMapping("/{slug}")
	@ResponseStatus(HttpStatus.OK)
	fun getBySlug(@PathVariable slug: String): NewsTag {
		return newsTagService.getBySlug(slug)
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	fun update(@RequestBody request: UpdateNewsTagRequest, @PathVariable id: Long): NewsTag {
		return newsTagService.update(request, id)
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: Long) {
		return newsTagService.delete(id)
	}
}