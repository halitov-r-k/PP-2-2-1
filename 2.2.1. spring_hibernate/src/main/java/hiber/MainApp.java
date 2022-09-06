package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) /*throws SQLException*/ {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "LastName1", "user1@mail.ru", new Car("Model1", 1));
      User user2 = new User("User2", "LastName2", "user2@mail.ru", new Car("Model2", 2));
      User user3 = new User("User3", "LastName3", "user3@mail.ru", new Car("Model3", 3));
      User user4 = new User("User4", "LastName4", "user4@mail.ru", new Car("Model4", 4));
      User user5 = new User("User5", "LastName5", "user5@mail.ru", new Car("Model5", 5));
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.printf("Car: id = %s, model = %s, series = %s %n",
                user.getCar().getId(), user.getCar().getModel(),user.getCar().getSeries());
      }
      String model = "Model1";
      int series = 1;
      User user = userService.findUser(model, series);
      System.out.printf("User with Car-model = %s, Car-model-series = %s has User-name = %s. %n", model, series, user.getFirstName());
      context.close();
   }
}
