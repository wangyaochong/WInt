package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.entity.test.TestEntity;
import program.repository.ICustomerRepo;
import program.repository.ICustomerRepoImpl;
import program.repository.test.ITestEntityRepo;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class TestRepository {
    @Resource
    ITestEntityRepo testEntityRepo;
    @Resource
    ICustomerRepo userRepo;


    @Test
    public void testCustomerRepoImpl(){
        ICustomerRepoImpl iCustomerRepo=new ICustomerRepoImpl();
    }

    @Test
    public void testQueryByExample(){
        TestEntity testEntityExample=new TestEntity();
        testEntityExample.setAge(1);
        Page<TestEntity> all = testEntityRepo.findAll(Example.of(testEntityExample), new PageRequest(0, 2));
        System.out.println(all);
        System.out.println(all.getContent());
    }

    @Test
    public void testRepository(){
        System.out.println(testEntityRepo.selfFindByName("hello"));
        System.out.println(testEntityRepo.findAll());
        System.out.println(testEntityRepo.findByName("1"));
        System.out.println(testEntityRepo.findAll( new Sort(Sort.Direction.DESC, "name")));
        System.out.println(testEntityRepo.findAll(new PageRequest(0, 2, Sort.Direction.DESC,"name")));
        System.out.println(testEntityRepo.findAll(new PageRequest(0, 2, Sort.Direction.DESC,"name")).getContent());
        System.out.println(testEntityRepo.findAll(new Specification<TestEntity>() {
            @Override
            public Predicate toPredicate(Root<TestEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("name"),"0");
            }
        }));
        System.out.println(testEntityRepo.findAll(new Specification<TestEntity>() {
            @Override
            public Predicate toPredicate(Root<TestEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaQuery.where(criteriaBuilder.lessThan(root.get("name"),"2")).getRestriction();
            }
        }));

    }
}
