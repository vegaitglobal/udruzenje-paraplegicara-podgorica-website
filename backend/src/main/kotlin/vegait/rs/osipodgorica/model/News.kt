package vegait.rs.osipodgorica.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.Lob
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate

@Entity
@Table(name = "news")
class News(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
	var title: String,
	var slug: String,

	@Lob
	@Column(columnDefinition = "LONGTEXT")
	var content: String,
	var imageRelativeUri: String? = null,
	@CreationTimestamp
	var createdAt: LocalDate?,
	@UpdateTimestamp
	var updatedAt: LocalDate?,
	@ManyToMany
	@JoinTable(name = "news_tags_tags",
			joinColumns = [JoinColumn(name = "news_id")],
			inverseJoinColumns = [JoinColumn(name = "tag_id")]
	)
	var tags: Set<NewsTag> = hashSetOf()
)