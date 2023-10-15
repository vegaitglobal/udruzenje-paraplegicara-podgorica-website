package vegait.rs.osipodgorica.model

import jakarta.persistence.*

@Entity
@Table(name = "locations", uniqueConstraints = [UniqueConstraint(columnNames = ["latitude", "longitude"])])
data class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    @Column(unique = true) var slug: String,
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    var description: String,
    var latitude: Double,
    var longitude: Double,
    var address: String?,
    var postalNumber: Long?,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true)
    var city: City,

    @ManyToMany
    @JoinTable(
        name = "accessibility_feature_location",
        joinColumns = arrayOf(JoinColumn(name = "location_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "accessibility_feature_id")),
    )
    var accessibilityFeatures: Set<AccessibilityFeature> = hashSetOf(),

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @JoinColumn(name = "location_id")
    var images: MutableList<LocationImage>? = mutableListOf(),
    var thumbnailUrl: String? = "",
    var email: String?,
    var phone: String?
)