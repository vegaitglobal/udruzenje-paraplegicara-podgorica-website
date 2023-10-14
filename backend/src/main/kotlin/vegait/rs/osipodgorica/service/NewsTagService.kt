package vegait.rs.osipodgorica.service

import org.springframework.stereotype.Service
import vegait.rs.osipodgorica.dto.CreateNewsTagRequest
import vegait.rs.osipodgorica.dto.UpdateNewsTagRequest
import vegait.rs.osipodgorica.model.NewsTag
import vegait.rs.osipodgorica.repository.NewsTagRepository
import vegait.rs.osipodgorica.utils.SlugGenerator

@Service
class NewsTagService(val newsTagRepo: NewsTagRepository) {

	fun store(request: CreateNewsTagRequest): NewsTag {
		val newsTagSlug = SlugGenerator.generateSlug(request.name)

		return newsTagRepo.save(NewsTag(name = request.name, slug = newsTagSlug))
	}

	fun get(id: Long): NewsTag {
		return newsTagRepo.findById(id).orElseThrow()
	}

	fun getBySlug(slug: String): NewsTag {
		return newsTagRepo.findBySlug(slug)?: throw Exception("News tag with slug $slug not found")
	}

	fun index(): List<NewsTag> {
		return newsTagRepo.findAll()
	}

	fun update(request: UpdateNewsTagRequest, id: Long): NewsTag {
		val newsTag = newsTagRepo.findById(id).orElseThrow()
		newsTag.name = request.name
		newsTag.slug = SlugGenerator.generateSlug(request.name)
		return newsTagRepo.save(newsTag)
	}

	fun delete(id: Long) {
		return newsTagRepo.deleteById(id)
	}
}