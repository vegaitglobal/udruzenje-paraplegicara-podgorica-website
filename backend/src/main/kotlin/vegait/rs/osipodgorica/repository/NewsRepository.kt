package vegait.rs.osipodgorica.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import vegait.rs.osipodgorica.model.News

@Repository
interface NewsRepository: JpaRepository<News, Long> {
	fun findBySlug(slug: String): News?

	@Query("SELECT n FROM News n JOIN n.tags t WHERE t.slug = :tagSlug")
	fun findByTag(tagSlug: String): List<News>
}