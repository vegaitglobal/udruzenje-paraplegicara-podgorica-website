package vegait.rs.osipodgorica.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vegait.rs.osipodgorica.model.NewsTag

@Repository
interface NewsTagRepository: JpaRepository<NewsTag, Long>  {

	fun findBySlug(slug: String): NewsTag?
}