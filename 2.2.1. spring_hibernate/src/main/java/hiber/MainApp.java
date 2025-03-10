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

      User sasha = new User("Sasha", "Popov", "sasha@mail.ru");
      User olga = new User("Olga", "Lvova", "olga@mail.ru");
      User dima = new User("Dima", "Ratnik", "dima@mail.ru");
      User sofia = new User("Sofia", "Cvetova", "sofia@mail.ru");

      Car hyundai = new Car ("Hyundai", 111);
      Car mazda = new Car ("Mazda", 222);
      Car niva = new Car ("Niva", 333);
      Car delorean = new Car ("Delorean", 444);

      sasha.setCar(hyundai);
      hyundai.setUser(sasha);
      olga.setCar(mazda);
      mazda.setUser(olga);
      dima.setCar(niva);
      niva.setUser(dima);
      sofia.setCar(delorean);
      delorean.setUser(sofia);

      userService.add(sasha);
      userService.add(olga);
      userService.add(dima);
      userService.add(sofia);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.printf(userService.getUserByCarId("Mazda", 222).getFirstName());
      context.close();
   }
}
