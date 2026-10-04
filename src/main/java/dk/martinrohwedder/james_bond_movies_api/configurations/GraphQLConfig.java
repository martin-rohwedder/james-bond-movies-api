package dk.martinrohwedder.james_bond_movies_api.configurations;

import dk.martinrohwedder.james_bond_movies_api.graphql.scalars.LocalDateScalar;
import dk.martinrohwedder.james_bond_movies_api.graphql.scalars.LocalDateTimeScalar;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(LocalDateTimeScalar.create())
                .scalar(LocalDateScalar.create())
                .scalar(ExtendedScalars.GraphQLLong);
    }
}
