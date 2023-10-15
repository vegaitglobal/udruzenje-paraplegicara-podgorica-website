package vegait.rs.osipodgorica.service

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vegait.rs.osipodgorica.dto.CreateNewsRequest
import vegait.rs.osipodgorica.dto.UpdateNewsRequest
import vegait.rs.osipodgorica.model.News
import vegait.rs.osipodgorica.model.NewsTag
import vegait.rs.osipodgorica.repository.NewsRepository
import vegait.rs.osipodgorica.utils.SlugGenerator
import java.time.LocalDate

@Service
@Transactional
class NewsService(val repository: NewsRepository,
                  val uploadService: FileUploadService,
                  val entityManager: EntityManager
) {

	fun store(request: CreateNewsRequest): News {
		val titleSlug = SlugGenerator.generateSlug(request.title)
		val currentDate = LocalDate.now()
		var tags = emptySet<NewsTag>()
		if (request.tagIds != null && !(request.tagIds?.isEmpty()!!)) {
			tags = request.tagIds!!
					.map { tag -> entityManager
							.getReference(NewsTag::class.java, tag)
					}.toSet()
		}
		val news = repository.save(
				News(
						title = request.title,
						slug = titleSlug,
						content = request.content,
						tags = tags,
						createdAt = currentDate,
						updatedAt = currentDate
				)
		)
		if (request.image != null) {
			val imagePath = uploadService.store(request.image!!, "news/" + news.id)
			news.imageRelativeUri = imagePath
		}
		return repository.save(news)
	}

	fun get(id: Long): News {
		return repository.findById(id).orElseThrow()
	}

	fun getBySlug(slug: String): News {
		return repository.findBySlug(slug) ?: throw Exception("News with slug $slug not found")
	}

	fun getByTag(tagSlug: String): List<News> {
		return repository.findByTag(tagSlug)
	}

	fun index(): List<News> {
		return repository.findAll()
	}

	fun update(request: UpdateNewsRequest, id: Long): News {
		val news = repository.findById(id).orElseThrow()
		val currentDate = LocalDate.now()
		news.title = request.title
		news.content = request.content
		news.slug = SlugGenerator.generateSlug(request.title)
		news.updatedAt = currentDate
		if (request.tagIds != null && !(request.tagIds?.isEmpty()!!)) {
			news.tags = emptySet<NewsTag>()
			news.tags = request.tagIds!!
					.map { tag -> entityManager
							.getReference(NewsTag::class.java, tag)
					}.toSet()
		}
		if (request.image != null) {
			val oldImage = news.imageRelativeUri
			val imagePath = uploadService.store(request.image!!, "news/" + news.id)
			if (oldImage != null) {
				uploadService.deleteFile(oldImage)
			}
			news.imageRelativeUri = imagePath
		}

		return repository.save(news)
	}

	fun delete(id: Long) {
		uploadService.deleteFolder("news/$id/")
		return repository.deleteById(id)
	}
}