package com.iqkv.incubator.sample.bookstore.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.iqkv.incubator.sample.bookstore.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableSpringDataWebSupport
@OpenAPIDefinition(info = @Info(title = "Bookstore API", version = "24.0.0"))
class ApplicationConfig {

}
