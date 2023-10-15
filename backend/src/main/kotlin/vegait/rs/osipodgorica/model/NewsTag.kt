package vegait.rs.osipodgorica.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "news_tags")
class NewsTag(
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
	var name: String,
	var slug: String,
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
	var news: Set<News> = hashSetOf()

)