package by.homework.regauthapp.configuration;

import by.homework.regauthapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


/**
 * Created by Сергей Зубов on 30.05.2019.
 */
@Configuration
@ComponentScans(value = {
        @ComponentScan("by.homework.regauthapp.dao"),
        @ComponentScan("by.homework.regauthapp.service"),
        @ComponentScan("by.homework.regauthapp.exception")
})
public class SessionFactoryConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }
}
