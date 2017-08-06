package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class EntityManagerService {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Bean
    public EntityManager entityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
