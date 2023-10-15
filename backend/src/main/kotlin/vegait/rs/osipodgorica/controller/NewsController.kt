package vegait.rs.osipodgorica.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import vegait.rs.osipodgorica.dto.CreateNewsRequest
import vegait.rs.osipodgorica.dto.UpdateNewsRequest
import vegait.rs.osipodgorica.model.News
import vegait.rs.osipodgorica.service.NewsService

@RestController
@RequestMapping("/api/v1/news")
@Transactional
class NewsController (val newsService: NewsService) {

	@PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
	@ResponseStatus(HttpStatus.CREATED)
	fun store(@ModelAttribute request: CreateNewsRequest): News {
		return newsService.store(request)
	}

	@GetMapping("/{slug}")
	@ResponseStatus(HttpStatus.OK)
	fun get(@PathVariable slug : String): News {
		return newsService.getBySlug(slug)
	}

	@GetMapping("/tags/{tagSlug}")
	@ResponseStatus(HttpStatus.OK)
	fun getByTag(@PathVariable tagSlug : String): List<News> {
		return newsService.getByTag(tagSlug)
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun index(): List<News> {
		return newsService.index()
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	fun update(@ModelAttribute request: UpdateNewsRequest, @PathVariable id: Long): News {
		return newsService.update(request, id)
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun delete(@PathVariable id: Long) {
		return newsService.delete(id)
	}
}