package vegait.rs.osipodgorica.model

import jakarta.persistence.*

@Entity
@Table(name = "archive")
data class Archive(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val description: String,
    @Column(unique = true)
    val path: String,
    @Column(unique = true)
    val name: String,
    val startDate: String,
    val endDate: String,
)
