package ch.rogerjaeggi.pokerstats.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Value("${db.driver}")
    private String driverClassName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.dialect}")
    private String dbDialect;

    @Value("${db.showSql}")
    private boolean dbShowSql;

    @Value("${db.formatSql}")
    private boolean dbFormatSql;

    @Value("${db.generateDdl}")
    private boolean dbGenerateDdl;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Inject
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Bean
    public DataSource dataSource() {
        if (env.acceptsProfiles(SPRING_PROFILE_DEVELOPMENT)) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).
                    addScript("db/schema.sql").
                    addScript("db/test-data.sql").build();
            return db;
        } else if (env.acceptsProfiles(SPRING_PROFILE_PRODUCTION)) {
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(driverClassName);
            basicDataSource.setUrl(dbUrl);
            basicDataSource.setUsername(dbUserName);
            basicDataSource.setPassword(dbPassword);
            basicDataSource.setInitialSize(3);
            return basicDataSource;
        } else {
            log.error("Your database connection pool configuration is incorrect! The application" +
                        "cannot start. Please check your Spring profile, current profiles are: {}",
                Arrays.toString(env.getActiveProfiles()));
            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(jpaAdapter());
        entityManagerFactory.setJpaProperties(jpaProperties());
        entityManagerFactory.setDataSource(dataSource());
        return entityManagerFactory;
    }

    public JpaVendorAdapter jpaAdapter() {
        HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setDatabasePlatform(dbDialect);
        jpaAdapter.setShowSql(dbShowSql);
        jpaAdapter.setGenerateDdl(dbGenerateDdl);
        return jpaAdapter;
    }

    private Properties jpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
                setProperty("hibernate.show_sql", Boolean.toString(dbShowSql));
                setProperty("hibernate.format_sql", Boolean.toString(dbFormatSql));
            }
        };
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

