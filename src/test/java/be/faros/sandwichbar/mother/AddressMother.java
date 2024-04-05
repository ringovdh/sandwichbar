package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.entity.Address;

public class AddressMother {

    public static Address createAddress() {
        Address address = new Address();
        address.setCity("New York");
        address.setStreet("Sesam Street");
        address.setPostcode(1000);
        address.setHouseNumber("10");
        return address;
    }

}
