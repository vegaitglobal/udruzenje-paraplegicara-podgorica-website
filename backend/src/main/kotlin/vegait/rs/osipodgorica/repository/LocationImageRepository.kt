package vegait.rs.osipodgorica.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vegait.rs.osipodgorica.model.LocationImage

@Repository
interface LocationImageRepository: JpaRepository<LocationImage, Long>