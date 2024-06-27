package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.AddressDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.User;

public class UserMother {

    public static User createNewUser1() {
        return createUser("User One", "user1@sandwichbar.be", "user_1");
    }

    public static User createNewUserTommy() {
        return createUser("Tommy", "tommy@sesame.com", "tommeke");
    }


    public static User createExistingUser1() {
        User user = createNewUser1();
        user.setId(1);
        return user;
    }

    public static User createExistingUserTommy() {
        User user = createNewUserTommy();
        user.setId(110);
        return user;
    }

    public static UpdateUserRequest createUpdateUserRequest(AddressDTO address) {
        return new UpdateUserRequest("New username", "User From Sandwichbar", address);
    }

    public static User createUserPinoWithAddress(Address address) {
        User user = createNewUser1();
        user.setAddress(address);
        return user;
    }

    public static User createUserTommyWithAddress(Address address) {
        User user = createNewUserTommy();
        user.setAddress(address);
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
