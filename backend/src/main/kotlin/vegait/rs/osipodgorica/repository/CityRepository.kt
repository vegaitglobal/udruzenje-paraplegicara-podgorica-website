package vegait.rs.osipodgorica.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.model.City
import java.util.Optional

@Repository
interface CityRepository: JpaRepository<City, Long> {
    fun findByName(name: String): Optional<City>
}