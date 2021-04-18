package assignment.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**      
 *
 * @author Maria
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    
    
     private DataSource getDatasource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/assignment_two?serverTimezone=Europe/Athens");
        datasource.setUsername("User");
        datasource.setPassword("user123");
        return datasource;
    }
    
    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }
    
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        DataSource datasource = getDatasource();
        sessionFactory.setDataSource(datasource);
        Properties properties = getHibernateProperties();
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setPackagesToScan("assignment.entity");
        return sessionFactory;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
    
    
}
