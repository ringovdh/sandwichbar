package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.request.LoginRequest;
import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.entity.Company;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mapper.UserMapper;

public class UserMother {

    private static final UserMapper userMapper = new UserMapper();

    public static User createNewUserPino() {
        return createUser("Pino", "pino@sesame.com", "S&cret-10");
    }

    public static User createNewUserTommy() {
        return createUser("Tommy", "tommy@sesame.com", "S&cret-11");
    }


    public static User createExistingUserPino() {
        User user = createNewUserPino();
        user.setId(100);
        return user;
    }

    public static User createExistingUserPinoDifferentPassword() {
        User user = createExistingUserPino();
        user.setPassword("Wr0ng-pwd");
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

    public static RegisterRequest createRegisterRequestTommy() {
        return new RegisterRequest("Tommy", "tommy@sesame.com", "S&cret-11");
    }

    public static RegisterRequest createRegisterRequestPino() {
        return new RegisterRequest("Pino", "pino@sesame.com", "S&cret-10");
    }

    public static LoginRequest createLoginRequestPino() {
        return new LoginRequest("pino@sesame.com", "S&cret-10");
    }

    public static LoginRequest createLoginRequestDifferentPasswordPino() {
        return new LoginRequest("pino.sesame.com", "Wr0ng-pwd");
    }

    public static RegisterRequest createInvalidEmailUserRegisterRequest() {
        return new RegisterRequest("Pino", "pino.sesame.com", "S&cret-10");
    }

    public static RegisterRequest createInvalidPasswordRegisterRequestPino() {
        return new RegisterRequest( "Pino", "pino@sesame.com", "Secret");
    }

    private static User createUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}