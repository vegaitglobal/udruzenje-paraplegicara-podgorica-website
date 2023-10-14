package vegait.rs.osipodgorica.model

import jakarta.persistence.*

@Entity
@Table(name = "cities")
class City (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
)