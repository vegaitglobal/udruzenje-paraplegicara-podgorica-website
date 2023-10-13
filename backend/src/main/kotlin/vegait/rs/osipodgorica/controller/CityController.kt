package vegait.rs.osipodgorica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vegait.rs.osipodgorica.model.City;
import vegait.rs.osipodgorica.repository.CityRepository;

@RestController
@RequestMapping("/api/v1/cities")
class CityController(val cityRepository:CityRepository) {

    @GetMapping
    fun index(): List<City> {
        return cityRepository.findAll();
    }
}
