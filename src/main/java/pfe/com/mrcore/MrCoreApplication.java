package pfe.com.mrcore;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pfe.com.mrcore.config.JerseyInitialization;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pfe.com.mrcore.core.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MrCoreApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(MrCoreApplication.class).run(args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {

		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyInitialization.class.getName());

		return registration;
	}
}
