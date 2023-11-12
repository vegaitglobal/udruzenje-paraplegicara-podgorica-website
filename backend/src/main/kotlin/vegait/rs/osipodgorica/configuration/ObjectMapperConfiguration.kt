package vegait.rs.osipodgorica.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {
    @Bean
    fun objectMapper() : ObjectMapper {
        val objectMapper = ObjectMapper().findAndRegisterModules()
        objectMapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        return objectMapper;
    }
}