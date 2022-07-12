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

      UserService userService = context.getBean(UserService.class);
      User user = new User("Anton", "Echmaev", "adsadas@yandex.ru");
      User user1 = new User("Evgeniy", "Kulil", "dsdzxc@yandex.ru");
      User user2 = new User("Kek", "Lolov", "sdfbsdnb@yandex.ru");
      Car car = new Car(user, "Bugatti", 231);
      Car car1 = new Car(user1, "Oka", 9009);
      Car car2 = new Car(user2, "Lal", 111);
      userService.add(user);
      userService.add(user1);
      userService.add(user2);
      userService.addCar(car);
      userService.addCar(car1);
      userService.addCar(car2);
      List<User> users = userService.listUsers();
      for (User user4 : users) {
         System.out.println("Id = "+user4.getId());
         System.out.println("First Name = "+user4.getFirstName());
         System.out.println("Last Name = "+user4.getLastName());
         System.out.println("Email = "+user4.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserbyCar(car));

      context.close();
   }
}
