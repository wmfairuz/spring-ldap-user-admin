package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import sample.dao.GroupRepoImpl;

@SpringBootApplication
@EnableConfigurationProperties
@Configuration
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    
    @Bean
    @ConfigurationProperties(prefix="amba.ldap.contextSource")
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }
    
    @Bean
    public GroupRepoImpl groupRepoImpl() {
    	return new GroupRepoImpl(ldapTemplate(contextSource()));
    }
}
