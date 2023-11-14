package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.City
import vegait.rs.osipodgorica.repository.CityRepository

@Component
@Order(1)
class CitySeeder(
    val cityRepository: CityRepository,
    val env: Environment,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (cityRepository.count() == 0L) {
            cityRepository.saveAll(cities())
        }
    }

    private fun cities(): List<City> {
        return arrayListOf(
            City(id = 1, name = "Podgorica"),
            City(id = 2, name = "Cetinje"),
            City(id = 3, name = "Bar"),
            City(id = 4, name = "Budva"),
            City(id = 5, name = "Kotor"),
            City(id = 6, name = "Tivat"),
            City(id = 7, name = "Herceg Novi"),
            City(id = 8, name = "Ulcinj"),
            City(id = 9, name = "Nikšić"),
            City(id = 10, name = "Bijelo Polje"),
            City(id = 11, name = "Pljevlja"),
            City(id = 12, name = "Berane"),
            City(id = 13, name = "Mojkovac"),
            City(id = 14, name = "Rozaje"),
            City(id = 15, name = "Plav"),
            City(id = 16, name = "Nepoznat")
        )
    }
}