package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }
   public User getUserbyCar(Car car) {
      String hql = "FROM Car where model = '"+ car.getModel() + "' and series = '"+ car.getSeries() +"'";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      Car car1 = (Car) query.getSingleResult();
      Query query1 = sessionFactory.getCurrentSession().createQuery("from User where car = '" + car1 + "'");
      return  (User) query1.getSingleResult();
   }


}
