package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.AccessibilityFeature
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.model.Location
import vegait.rs.osipodgorica.repository.AccessibilityFeatureRepository
import vegait.rs.osipodgorica.repository.CategoryRepository
import vegait.rs.osipodgorica.repository.CityRepository
import vegait.rs.osipodgorica.repository.LocationRepository

@Component
class InitSeeder(
    val featuresRepo: AccessibilityFeatureRepository,
    val categoryRepo: CategoryRepository,
    val cityRepo: CityRepository,
    val locationRepo: LocationRepository,
    val env: Environment,
) : CommandLineRunner {

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
            Category(
                id = 1,
                name = "Objekti državnih organa",
                relativeUrl = "uploads/categories/1/m-drzavni-organi.png",
            ),
            Category(id = 2, name = "Lokalna samouprava", relativeUrl = "uploads/categories/2/m-drzavni-organi.png"),
            Category(id = 3, name = "Zdravstvo", relativeUrl = "uploads/categories/3/m-zdravlje.png"),
            Category(id = 4, name = "Obrazovanje", relativeUrl = "uploads/categories/4/m-obrazovanje.png"),
            Category(id = 5, name = "Kultura", relativeUrl = "uploads/categories/5/m-kultura.png"),
            Category(
                id = 6,
                name = "Otvoreni i zatvoreni sportski i rekreativni objekti",
                relativeUrl = "uploads/categories/6/m-sport.png",
            ),
            Category(id = 7, name = "Saobraćajni terminali", relativeUrl = "uploads/categories/7/m-saobracaj.png"),
            Category(id = 8, name = "Pošte", relativeUrl = "uploads/categories/8/m-posta.png"),
            Category(id = 9, name = "Banke", relativeUrl = "uploads/categories/9/m-banke.png"),
            Category(id = 10, name = "Trgovački objekti", relativeUrl = "uploads/categories/10/m-trgovina.png"),
            Category(id = 11, name = "Turistički objekti", relativeUrl = "uploads/categories/11/m-turizam.png"),
            Category(
                id = 12,
                name = "Ugostiteljski objekti",
                relativeUrl = "uploads/categories/12/m-ugostiteljstvo.png",
            ),
            Category(id = 13, name = "Vjerski objekti", relativeUrl = "uploads/categories/13/m-crkve.png"),
            Category(id = 14, name = "Telekomunikacije", relativeUrl = "uploads/categories/14/m-telekomunikacije.png"),
            Category(id = 15, name = "Zabava", relativeUrl = "uploads/categories/15/m-zabava.png"),
        )
    }

    private fun locations(): List<Location> {
        val publicFacility = categoryRepo.findByName("Objekti državnih organa")
        val culturalFacility = categoryRepo.findByName("Kultura")
        val hospital = categoryRepo.findByName("Zdravstvo")
        val bank = categoryRepo.findByName("Banke")
        val trafficFacility = categoryRepo.findByName("Saobraćajni terminali")
        val commercialFacilities = categoryRepo.findByName("Trgovački objekti")

        val parking = featuresRepo.findByName("Parking mjesto")
        val ramp = featuresRepo.findByName("Rampa")
        val toalet = featuresRepo.findByName("Toalet")
        val lift = featuresRepo.findByName("Lift")

        val podgorica = cityRepo.findById(1).orElseThrow()
        val niksic = cityRepo.findById(9).orElseThrow()
        val cetinje = cityRepo.findById(2).orElseThrow()

        return arrayListOf(
            Location(
                name = "KBC",
                description = "Kliničko bolnički centar CG",
                category = hospital,
                accessibilityFeatures = hashSetOf(parking, toalet),
                latitude = 42.437966,
                longitude = 19.246028,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "KIC Budo Tomović",
                description = "Kulturni objekat",
                category = culturalFacility,
                accessibilityFeatures = hashSetOf(toalet),
                latitude = 42.446105,
                longitude = 19.264439,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "MUP",
                description = "Ministarstvo unutrašnjih poslova",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.442171,
                longitude = 19.252682,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "Crnogorsko Komercijalna Banka",
                description = "Crnogorsko Komercijalna Banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.441631066437786,
                longitude = 19.247161806315603,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.44190356595263,
                longitude = 19.247437111656154,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka 2",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.442468066159144,
                longitude = 19.249060753384356,
                address = "",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "MINISTARSTVO ZA LJUDSKA I MANJINSKA PRAVA",
                description = "U sklopu ministarstva se nalazi i Direktorat za unapredjenje i zastitu ljudskih prava, koji se izmedju ostalog bavi i sa temama koje se ticu lica sa invaliditetom.",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, lift),
                latitude = 42.4421747388227,
                longitude = 19.246871165554012,
                address = "46, Rimski Trg, Kruševac",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "shoping-centar-mall-of-montenegro",
                description = "Shoping centar Mall of Montenegro u sklopu kojeg se nalazi zelena pijaca, moderni shoping centar i savremeni hotel \"Ramada\" u kojem su dvije sobe pristupačne za lica koja su korisnici invalidskih kolica. U sklopu objekta se nalazi hipermarket \"Roda\", banka, bankomati, apoteka, prodavnice tehničkih uređaja, sportske opreme, garderobe, obuće, igraonica za djecu, kladionica, prodavnica kozmetičkih proizvoda, teretana, spa&welness centar, kuglana, restorani, autoperionica...",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, lift),
                latitude = 42.432195991907406,
                longitude = 19.26276325396725,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "njegosev-park",
                description = "njegosev-park",
                category = culturalFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.385984654613424,
                longitude = 18.925302028656006,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = cetinje,
            ),
            Location(
                name = "voli-29-podgorica",
                description = "voli-29-podgorica",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.46607432673881,
                longitude = 19.29769992828369,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "nas-diskont-podgorica",
                description = "Objekat nas diskont ima uradjena zakosenja prije ulaska u prodajni prostor. Unutrasnja komunikacija licima sa invaliditetom je moguca, a ispred samog objekta nalazi se obiljezeno vise parking mjesta.\n" +
                    "Radno vrijeme: 07:00 - 22:00 svakog dana",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.42097757031943,
                longitude = 19.25699472427368,
                address = "4 Jula bb, E80",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "benzinska-pumpa-eko-petrol-bs-podgorica-6",
                description = "Iza objekta se nalazi toalet do koga vodi rampa sa rukohvatom. Neposredno do rampe se nalaze obiljezena dva parking mjesta. U sklopu objekta se nalazi cafe, prodavnica i auto perionica.\n" +
                    "Pumpa se nalazi sa desne strane magistralnog pumpa od pravca naselja Zabjelo u pravcu takozvanih cetinjskih semafora.\n" +
                    "Radno vrijeme: 0:00 - 24:00 svakog dana.",
                category = trafficFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, parking),
                latitude = 42.43025134141357,
                longitude = 19.23166662454605,
                address = "Vojisavljevica bb, E762, Zabjelo",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "dom-zdravlja-tuzi",
                description = "Ulazna rampa bez rukohvata. Toalet",
                category = trafficFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, parking),
                latitude = 42.36343149754172,
                longitude = 19.329114486755316,
                address = "Tuzi, E-762",
                postalNumber = 81000,
                city = podgorica,
            ),
            Location(
                name = "idea-kapino-polje-niksic",
                description = "Na izlazu iz Niksica, prema granici Bosne i Hercegovine, do Vukovog mosta, nalazi se prodajni objekat IDEA.\n" +
                    "Objekat ima na ulazu rampu bez ograde.\n" +
                    "Radno vrijeme: pon - ned: 07:00 - 22:00",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp, toalet, parking),
                latitude = 42.78118691504842,
                longitude = 18.92085760831833,
                address = "Tuzi, E-762",
                postalNumber = 81000,
                city = niksic,
            ),
        )
    }
}
