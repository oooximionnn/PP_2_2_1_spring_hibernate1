package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      Car car = new Car("Audi R", 8);
      Car car1 = new Car("GT", 40);
      Car car2 = new Car("Corvette ZR", 1);

      UserService userService = context.getBean(UserService.class);

      User user = new User("Anton", "Echmaev", "adsadas@yandex.ru", car);
      User user1 = new User("Evgeniy", "Kulin", "dsdzxc@yandex.ru", car1);
      User user2 = new User("Kek", "Lolov", "sdfbsdnb@yandex.ru", car2);
      userService.add(user);
      userService.add(user1);
      userService.add(user2);
      List<User> users = userService.listUsers();

      for (User user4 : users) {
         System.out.println("Id = "+user4.getId());
         System.out.println("First Name = "+user4.getFirstName());
         System.out.println("Last Name = "+user4.getLastName());
         System.out.println("Email = "+user4.getEmail());
         System.out.println();
      }

      System.out.println(userService.getbyCar(car));

      context.close();
   }
}
