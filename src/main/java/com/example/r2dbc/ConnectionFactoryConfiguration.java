package com.example.r2dbc;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DatabaseClient;

@Configuration
class ConnectionFactoryConfiguration {

	@Bean
    DatabaseClient databaseClient() {
		return DatabaseClient.create(connectionFactory());
	}

	@Bean
    ConnectionFactory connectionFactory() {

		PostgresqlConnectionConfiguration config =
				PostgresqlConnectionConfiguration.builder()
						.database("order")
						.username("user")
						.password("password")
						.host("localhost")
						.build()

				;
		return new PostgresqlConnectionFactory(config);
	}

}
