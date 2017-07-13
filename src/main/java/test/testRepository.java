package test;

import com.mysema.query.types.Expression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.StringPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.entity.TestEntity;
import program.repository.TestEntityRepo;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class testRepository {


    @Resource
    TestEntityRepo testEntityRepo;
    @Test
    public void testRepository(){
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
