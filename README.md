# Projekat za Udruženje paraplegičara Podgorice - Code For Cause 2023
## Stack
Backend: Spring Boot, Kotlin: dependency `JDK 17`   
Frontend: Next JS: dependency `NodeJS 18.18.1 LTS`
# Local setup
## Backend 

### Podesiti bazu kroz doker (ili lokalno)
```bash
docker run -p 3306:3306 -d --name osi-db-con \
--env MYSQL_USER=osi-user \
--env MYSQL_PASSWORD=secret \
--env MYSQL_ROOT_PASSWORD=root \
--env MYSQL_DATABASE=osi \
mysql:latest
```    
### Kako pokrenuti aplikaciju:
1. Potrebno je skinuti [JDK 17](https://adoptium.net/en-GB/temurin/releases/?version=17)  i setovati JAVA_HOME env varijablu
   da pokazuje na jdk koji smo skinuli. Putanja treba da upućuje na root folder unutar kod je bin folder.

2. U projektu postoji Gradle wrapper koji možemo koristiti da podignemo aplikaciju.

Unutar `backend` foldera izvršimo sledeće komande:

#### Linux
```bash
./gradlew bootRun
```

#### Windows
```bash
./gradlew.bat bootRun
```

### Pokretanje testova

#### Linux
```bash
./gradlew test
```

#### Windows
```bash
./gradlew.bat test
```

#### Napomene:
Prilikom prvog podizanja aplikacije automatski će biti kreirani određeni testni podaci - kategorije, elementi pristupačnosti i par lokacija koje možemo iskoristiti da istestiramo osnovne funkcionalnosti.

#### API je dostupn na [http://localhost:8080](http://localhost:8080) a swagger UI na [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Frontend

Unutar frontend projekta izvršimo:

```bash
npm run dev
```

#### Otvorimo [http://localhost:3000](http://localhost:3000) da bismo pristupili klijent aplikaciji