package vegait.rs.osipodgorica.seeder

import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import vegait.rs.osipodgorica.model.*
import vegait.rs.osipodgorica.repository.*
import java.time.LocalDate

@Component
class InitSeeder(
    val featuresRepo: AccessibilityFeatureRepository,
    val categoryRepo: CategoryRepository,
    val cityRepo: CityRepository,
    val locationRepo: LocationRepository,
    val newsTagRepo: NewsTagRepository,
    val newsRepo: NewsRepository,
    val archiveRepo: ArchiveRepository,
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

        if (newsTagRepo.count() == 0L) {
            newsTagRepo.saveAll(newsTags())
        }

        if (newsRepo.count() == 0L) {
            newsRepo.saveAll(news())
        }

        archiveRepo.takeIf { it.count() == 0L }?.saveAll(archive)
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
            Category(
                id = 2,
                name = "Lokalna samouprava",
                relativeUrl = "uploads/categories/2/m-drzavni-organi.png",
            ),
            Category(
                id = 3,
                name = "Zdravstvo",
                relativeUrl = "uploads/categories/3/m-zdravlje.png",
            ),
            Category(
                id = 4,
                name = "Obrazovanje",
                relativeUrl = "uploads/categories/4/m-obrazovanje.png",
            ),
            Category(id = 5, name = "Kultura", relativeUrl = "uploads/categories/5/m-kultura.png"),
            Category(
                id = 6,
                name = "Otvoreni i zatvoreni sportski i rekreativni objekti",
                relativeUrl = "uploads/categories/6/m-sport.png",
            ),
            Category(
                id = 7,
                name = "Saobraćajni terminali",
                relativeUrl = "uploads/categories/7/m-saobracaj.png",
            ),
            Category(id = 8, name = "Pošte", relativeUrl = "uploads/categories/8/m-posta.png"),
            Category(id = 9, name = "Banke", relativeUrl = "uploads/categories/9/m-banke.png"),
            Category(
                id = 10,
                name = "Trgovački objekti",
                relativeUrl = "uploads/categories/10/m-trgovina.png",
            ),
            Category(
                id = 11,
                name = "Turistički objekti",
                relativeUrl = "uploads/categories/11/m-turizam.png",
            ),
            Category(
                id = 12,
                name = "Ugostiteljski objekti",
                relativeUrl = "uploads/categories/12/m-ugostiteljstvo.png",
            ),
            Category(
                id = 13,
                name = "Vjerski objekti",
                relativeUrl = "uploads/categories/13/m-crkve.png",
            ),
            Category(
                id = 14,
                name = "Telekomunikacije",
                relativeUrl = "uploads/categories/14/m-telekomunikacije.png",
            ),
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
                address = "Ljubljanska bb",
                postalNumber = 81000,
                city = podgorica,
                slug = "kbc",
                email = "kbc@gmail.com",
                phone = "069439289",
            ),
            Location(
                name = "KIC Budo Tomović",
                description = "Kulturni objekat",
                category = culturalFacility,
                accessibilityFeatures = hashSetOf(toalet),
                latitude = 42.446105,
                longitude = 19.264439,
                address = "Vaka Đurovića",
                postalNumber = 81000,
                city = podgorica,
                slug = "kic",
                email = "kicbudo@gmail.com",
                phone = "069439280",
            ),
            Location(
                name = "MUP",
                description = "Ministarstvo unutrašnjih poslova",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.442171,
                longitude = 19.252682,
                address = "Bulevar Svetog Petra Cetinjskog 22",
                postalNumber = 81000,
                city = podgorica,
                slug = "mup",
                email = "mup@gmail.com",
                phone = "069429289",
            ),
            Location(
                name = "Crnogorska Komercijalna Banka",
                description = "Crnogorsko Komercijalna Banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.441631066437786,
                longitude = 19.247161806315603,
                address = "Moskovska",
                postalNumber = 81000,
                city = podgorica,
                slug = "ckb",
                email = "crnogorska-komercijalna@gmail.com",
                phone = "069111289",
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.44190356595263,
                longitude = 19.247437111656154,
                address = "Bulevar Svetog Petra Cetinjskog",
                postalNumber = 81000,
                city = podgorica,
                slug = "filijala-ckb",
                email = "ckb@office.com",
                phone = "068999289",
            ),
            Location(
                name = "Filijala Crnogorska Komercijalna Banka 2",
                description = "filijala-crnogorska-komercijalna-banka",
                category = bank,
                accessibilityFeatures = hashSetOf(ramp, toalet),
                latitude = 42.442468066159144,
                longitude = 19.249060753384356,
                address = "Bratstva i Jedinstva 28",
                postalNumber = 81000,
                city = podgorica,
                slug = "filijala-ckb-2",
                email = "ckb-filijala2@gmail.com",
                phone = "069439282",
            ),
            Location(
                name = "Ministarstvo za ljudska i manjinska prava",
                description = "U sklopu ministarstva se nalazi i Direktorat za unapredjenje i zastitu ljudskih prava, koji se izmedju ostalog bavi i sa temama koje se ticu lica sa invaliditetom.",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, lift),
                latitude = 42.4421747388227,
                longitude = 19.246871165554012,
                address = "46, Rimski Trg, Kruševac",
                postalNumber = 81000,
                city = podgorica,
                slug = "mzmp",
                email = "ministarstvo_za_ljudska_prava@gmail.com",
                phone = "069439200",
            ),
            Location(
                name = "Mall of Montenegro",
                description = "Shopping centar Mall of Montenegro u sklopu kojeg se nalazi zelena pijaca, moderni shoping centar i savremeni hotel \"Ramada\" u kojem su dvije sobe pristupačne za lica koja su korisnici invalidskih kolica. U sklopu objekta se nalazi hipermarket \"Roda\", banka, bankomati, apoteka, prodavnice tehničkih uređaja, sportske opreme, garderobe, obuće, igraonica za djecu, kladionica, prodavnica kozmetičkih proizvoda, teretana, spa&welness centar, kuglana, restorani, autoperionica...",
                category = publicFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, lift),
                latitude = 42.432195991907406,
                longitude = 19.26276325396725,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = podgorica,
                slug = "mall-of-mne",
                email = "mallofmontenegro@gmail.com",
                phone = "069439289",
            ),
            Location(
                name = "Njegošev park",
                description = "Park u Podgorici",
                category = culturalFacility,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.385984654613424,
                longitude = 18.925302028656006,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = cetinje,
                slug = "njegosev-park",
                email = "",
                phone = "",
            ),
            Location(
                name = "Voli 29 Podgorica",
                description = "voli-29-podgorica",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.46607432673881,
                longitude = 19.29769992828369,
                address = "Bulevar Save Kovačevića br. 74",
                postalNumber = 81000,
                city = podgorica,
                slug = "voli",
                email = "volivasvoli@gmail.com",
                phone = "",
            ),
            Location(
                name = "Naš diskont Podgorica",
                description = "Objekat nas diskont ima uradjena zakosenja prije ulaska u prodajni prostor. Unutrasnja komunikacija licima sa invaliditetom je moguca, a ispred samog objekta nalazi se obiljezeno vise parking mjesta.\n" +
                    "Radno vrijeme: 07:00 - 22:00 svakog dana",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp),
                latitude = 42.42097757031943,
                longitude = 19.25699472427368,
                address = "4 Jula bb, E80",
                postalNumber = 81000,
                city = podgorica,
                slug = "lakovic",
                email = "lakijemalonervozan@gmail.com",
                phone = "020439289",
            ),
            Location(
                name = "Benzinska pumpa eko-petrol podgorica",
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
                slug = "benzinska",
                email = "eko@gmail.com",
                phone = "020439289",
            ),
            Location(
                name = "Dom zdravlja Tuzi",
                description = "Ulazna rampa bez rukohvata. Toalet",
                category = trafficFacility,
                accessibilityFeatures = hashSetOf(ramp, toalet, parking),
                latitude = 42.36343149754172,
                longitude = 19.329114486755316,
                address = "Tuzi, E-762",
                postalNumber = 81206,
                city = podgorica,
                slug = "doma-zdravlja-tuzi",
                email = "domzdravlja_tuzi@gmail.com",
                phone = "067439289",
            ),
            Location(
                name = "Idea Kapino polje Nikšić",
                description = "Na izlazu iz Niksica, prema granici Bosne i Hercegovine, do Vukovog mosta, nalazi se prodajni objekat IDEA.\n" +
                    "Objekat ima na ulazu rampu bez ograde.\n" +
                    "Radno vrijeme: pon - ned: 07:00 - 22:00",
                category = commercialFacilities,
                accessibilityFeatures = hashSetOf(ramp, toalet, parking),
                latitude = 42.78118691504842,
                longitude = 18.92085760831833,
                address = "Nikšić bb",
                postalNumber = 81420,
                city = niksic,
                slug = "idea",
                email = "idea@gmail.com",
                phone = "069439289",
            ),
        )
    }

    private fun newsTags(): List<NewsTag> {
        return arrayListOf(
            NewsTag(id = 1, name = "Vijesti", slug = "vijesti"),
            NewsTag(id = 2, name = "Sport", slug = "sport"),
            NewsTag(id = 3, name = "Projekti", slug = "projekti"),
            NewsTag(id = 4, name = "Nekategorisano", slug = "nekategorisano"),
            NewsTag(id = 5, name = "Saopštenja", slug = "saopstenja"),
        )
    }

    val CONTENT_1 = """
	   <div class="blog-post-body">
	      <p style="text-align: justify;">Dana 24 i 25. aprila 2021. godine, takmičari stonoteniskog kluba osoba sa invaliditetom “Luča” će se sastati sa stonoteniskim klubom “Budim” iz Berana, u meču Plej offa za ulazak u Super ligu Crne Gore.</p>
	      <p style="text-align: justify;">Stonoteniski klub “Luča” se u sezoni 2020/2021 takmičio u Prvoj Crnogorskoj ligi u kojoj je zauzeo drugo mjesto sa skorom od četiri pobjede i dva poraza, iza kluba “Kotor”.</p>
	      <p style="text-align: justify;">Za ekipu nastupaju: Pjetro Paljušević, Zoran Bašanović, Dejan Bašanović, Zoran Čabarkapa, Miljan Cerović i Đuro Krivokapić.</p>
	      <p style="text-align: justify;">Mečevi Plej offa se odigravaju u sportskoj dvorani “Voco” u Podgorici sa početkom u 13:00 h.</p>
	   </div>
	""".trimIndent()

    val CONTENT_2 = """
    <div class="blog-post-body">
       <p>Na Evropskoj univerzijadi koja se održava u Republici Hrvatskoj, u Zagrebu i Rijeci, Crnu Goru u stonom tenisu za lica sa invaliditetom predstavljaju takmičarke stonoteniskog kluba “Luča” Samra Kojić i Slobodanka Gurešić.</p>
       <p><img class="alignleft size-medium wp-image-2167" src="http://www.osipodgorica.me/wp-content/uploads/2016/07/13735628_826144477520570_8372667437946295870_o-300x225.jpg" alt="13735628_826144477520570_8372667437946295870_o" width="300" height="225">Naše predstavnice se takmiče u klasama 5 – Samra Kojić i 4 – Slobodanka Gurešić. U klasama 4 i 5 učestvuju takmičari koji su korisnici invalidskih kolica. Osim sportiskinja Crnu Goru na Univerzijadi predstavlja i trener Čedomir Damjanović.</p>
       <p>Nadamo se da će nas sa takmičenja obradovati dobrim rezultatima. Srećno!</p>
       <p>&nbsp;</p>
    </div>
    """.trimIndent()

    private fun news(): List<News> {
        val uncategorizedTag = newsTagRepo.findBySlug("nekategorisano")
        val sportTag = newsTagRepo.findBySlug("sport")
        val projectTag = newsTagRepo.findBySlug("projekti")

        return arrayListOf(
            News(
                id = 1,
                title = "Play off za ulazak u super ligu",
                slug = "play-off-za-ulazak-u-super-ligu",
                content = CONTENT_1,
                createdAt = LocalDate.now().minusMonths(9),
                updatedAt = LocalDate.now().minusMonths(9),
                imageRelativeUri = "uploads/news/1/liga-1.jpg",
                tags = hashSetOf(sportTag!!, projectTag!!),
            ),
            News(
                id = 2,
                title = "Evropska Univerzijada",
                slug = "evropska-univerzijada",
                content = CONTENT_2,
                createdAt = LocalDate.now().minusMonths(19),
                updatedAt = LocalDate.now().minusMonths(19),
                imageRelativeUri = "uploads/news/2/vijest-2.jpg",
                tags = hashSetOf(sportTag, uncategorizedTag!!),
            ),
        )
    }

    private val archive: List<Archive> = listOf(Archive(id = 1, description = "bilans stanja", "uploads/archive/Bilans-stanja.pdf", name = "Bilans stanja"))
}
