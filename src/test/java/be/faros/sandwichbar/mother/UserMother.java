package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.entity.Company;
import be.faros.sandwichbar.entity.User;

public class UserMother {

    public static User createNewUserPino() {
        return createUser("Pino", "pino@sesame.com", "pino");
    }

    public static User createNewUserTommy() {
        return createUser("Tommy", "tommy@sesame.com", "tommeke");
    }


    public static User createExistingUserPino() {
        User user = createNewUserPino();
        user.setId(100);
        return user;
    }

    public static User createExistingUserTommy() {
        User user = createNewUserTommy();
        user.setId(110);
        return user;
    }

    public static User createUserPinoWithCompany(Company company) {
        User user = createNewUserPino();
        user.setCompany(company);
        return user;
    }

    public static User createUserTommyWithCompany(Company company) {
        User user = createNewUserTommy();
        user.setCompany(company);
        return user;
    }


    private static User createUser(String name, String email, String userName) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(userName);
        return user;
    }
}
