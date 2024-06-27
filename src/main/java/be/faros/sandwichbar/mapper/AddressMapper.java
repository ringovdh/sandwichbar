package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.AddressDTO;
import be.faros.sandwichbar.entity.Address;

public class AddressMapper {

    public static Address mapAddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.id());
        address.setStreet(addressDTO.street());
        address.setHouseNumber(addressDTO.houseNumber());
        address.setPostcode(addressDTO.postcode());
        address.setCity(addressDTO.city());
        return address;
    }

    public static AddressDTO mapAddressToDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getPostcode(),
                address.getCity()
        );
    }
}
