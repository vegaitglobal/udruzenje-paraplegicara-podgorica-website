package vegait.rs.osipodgorica.model

import jakarta.persistence.*

@Entity
@Table(name = "location_images")
class LocationImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var relativeUrl: String? = null,
)