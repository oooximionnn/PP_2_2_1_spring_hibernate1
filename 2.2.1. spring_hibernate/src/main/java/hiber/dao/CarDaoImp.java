package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class CarDaoImp implements CarDao{

    private SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car where model=:model and series =: series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        Car car = (Car) query.getSingleResult();
        Query query2 = sessionFactory.getCurrentSession().createQuery("from User where car=:car");
        query2.setParameter("car", car);
        User user = (User) query2.getSingleResult();
        return user;
    }
}
