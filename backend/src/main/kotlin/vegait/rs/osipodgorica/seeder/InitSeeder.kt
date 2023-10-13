package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.AccessibilityFeature
import vegait.rs.osipodgorica.model.Location
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.repository.AccessibilityFeatureRepository
import vegait.rs.osipodgorica.repository.CategoryRepository
import vegait.rs.osipodgorica.repository.LocationRepository

@Component
class InitSeeder(
    val featuresRepo: AccessibilityFeatureRepository,
    val categoryRepo: CategoryRepository,
    val locationRepo: LocationRepository,
    val env: Environment
): CommandLineRunner {

    override fun run(vararg args: String?) {
        if (featuresRepo.count() == 0L) {
            featuresRepo.saveAll(features())
        }

        if (categoryRepo.count() == 0L) {
            categoryRepo.saveAll(categories())
        }

        if (env.activeProfiles.contains("dev") && locationRepo.count() == 0L) {
            locationRepo.saveAll(locations())
        }
    }

    private fun features(): List<AccessibilityFeature> {
        return arrayListOf(
            AccessibilityFeature(id = 1, name = "Rampa", "uploads/features/1/rampa.png"),
            AccessibilityFeature(id = 2, name = "Lift", "uploads/features/2/lift.png"),
            AccessibilityFeature(id = 3, name = "Parking mjesto", "uploads/features/3/parking.png"),
            AccessibilityFeature(id = 4, name = "Toalet", "uploads/features/4/toalet.png"),

//            nemamo ikonice pa su privremeno zakomentarisani
//            AccessibilityFeature(name = "Taktilna crta vodilja"),
//            AccessibilityFeature(name = "Ulazni prostor"),
//            AccessibilityFeature(name = "Kupatilo"),
//            AccessibilityFeature(name = "Ulaz u vodu na plaži i bazenu"),
//            AccessibilityFeature(name = "Mjesto u gledalištu"),
//            AccessibilityFeature(name = "Šalter"),
//            AccessibilityFeature(name = "Pult"),
//            AccessibilityFeature(name = "Induktivna petlja ili transmisioni obruč"),
//            AccessibilityFeature(name = "Oglasni pano"),
//            AccessibilityFeature(name = "Orjentacioni plan za kretanje u objektu"),
//            AccessibilityFeature(name = "Stajalište i peron"),
//            AccessibilityFeature(name = "Semafor"),
//            AccessibilityFeature(name = "Pješački prelazi"),
//            AccessibilityFeature(name = "Raskrsnica")
        )
    }

    private fun categories(): List<Category> {
        return arrayListOf(
          Category(id = 1, name = "Objekti državnih organa", relativeUrl = "/uploads/categories/1/m-drzavni-organi.png"),
          Category(id = 2, name = "Lokalna samouprava", relativeUrl = "/uploads/categories/2/m-drzavni-organi.png"),
          Category(id = 3, name = "Zdravstvo", relativeUrl = "/uploads/categories/3/m-zdravlje.png"),
          Category(id = 4, name = "Obrazovanje", relativeUrl = "/uploads/categories/4/m-obrazovanje.png"),
          Category(id = 5, name = "Kultura", relativeUrl = "/uploads/categories/5/m-kultura.png"),
          Category(id = 6, name = "Otvoreni i zatvoreni sportski i rekreativni objekti", relativeUrl = "/uploads/categories/6/m-sport.png"),
          Category(id = 7, name = "Saobraćajni terminali", relativeUrl = "/uploads/categories/7/m-saobracaj.png"),
          Category(id = 8, name = "Pošte", relativeUrl = "/uploads/categories/8/m-posta.png"),
          Category(id = 9, name = "Banke", relativeUrl = "/uploads/categories/9/m-banke.png"),
          Category(id = 10, name = "Trgovački objekti", relativeUrl = "/uploads/categories/10/m-trgovina.png"),
          Category(id = 11, name = "Turistički objekti", relativeUrl = "/uploads/categories/11/m-turizam.png"),
          Category(id = 12, name = "Ugostiteljski objekti", relativeUrl = "/uploads/categories/12/m-ugostiteljstvo.png"),
          Category(id = 13, name = "Vjerski objekti", relativeUrl = "/uploads/categories/13/m-crkve.png"),
          Category(id = 14, name = "Telekomunikacije", relativeUrl = "/uploads/categories/14/m-telekomunikacije.png"),
          Category(id = 15, name = "Zabava", relativeUrl = "/uploads/categories/15/m-zabava.png"),
        )
    }

    private fun locations(): List<Location> {
        val publicFacility = categoryRepo.findByName("Objekti državnih organa")
        val culturalFacility = categoryRepo.findByName("Kultura")
        val hospital = categoryRepo.findByName("Obrazovanje")
        val parking = featuresRepo.findByName("Parking mjesto")
        val ramp = featuresRepo.findByName("Rampa")
        val loo = featuresRepo.findByName("Toalet")
        val bank = categoryRepo.findByName("Banke")

        return arrayListOf(
            Location(
                name = "KBC",
                description = "Kliničko bolnički centar CG",
                category = hospital,
                accessibilityFeatures = hashSetOf(parking, loo),
                latitude = 42.437966,
                longitude = 19.246028
            ),
            Location(
                name = "KIC Budo Tomović",
                description = "Kulturni objekat",
                category = culturalFacility,
                accessibilityFeatures = hashSetOf(loo),
                latitude = 42.446105,
                longitude = 19.264439
            ),
            Location(
                name = "MUP",
                description = "Ministarstvo unutrašnjih poslova",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.442171,
                longitude = 19.252682
            ),
            Location(
                name = "MUP",
                description = "Ministarstvo unutrašnjih poslova",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.442171,
                longitude = 19.252682
            ),
            Location(
                name = "Crnogorsko Komercijalna Banka",
                description = "Crnogorsko Komercijalna Banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, loo),
                latitude = 42.441631066437786,
                longitude = 19.247161806315603
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, loo),
                latitude = 42.44190356595263,
                longitude = 19.247437111656154
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka 2",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, loo),
                latitude = 42.442468066159144,
                longitude = 19.249060753384356
            )
        )
    }
}