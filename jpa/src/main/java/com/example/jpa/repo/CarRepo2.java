package com.example.jpa.repo;

import com.example.jpa.entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CarRepo2 {
    @PersistenceContext
    private EntityManager em;
    public List<Car> listCar(Integer id) {
        StringBuilder qs = new StringBuilder();
        qs.append("select * from car ");
//        qs.append("inner join product.categories category inner join product.merchantStore pm ");
        qs.append("where car_id=:id");
//        qs.append("group by category.id");

        String hql = qs.toString();
        Query q = this.em.createQuery(hql);

        q.setParameter("car_id", id);

        @SuppressWarnings("unchecked")
        List<Car> c =  q.getResultList();

        return c;
    }
}
