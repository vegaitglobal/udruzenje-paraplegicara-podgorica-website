package vegait.rs.osipodgorica.model

import jakarta.persistence.*
import java.text.SimpleDateFormat
import java.time.LocalDate

@Entity
@Table(name = "archive")
data class Archive(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val description: String,
    @Column(unique = true)
    val relativeUrl: String,
    @Column(unique = true)
    val name: String,
    val startDateInstance: LocalDate,
    val endDateInstance: LocalDate,
)
