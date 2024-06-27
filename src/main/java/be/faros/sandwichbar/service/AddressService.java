package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.AddressDTO;
import be.faros.sandwichbar.entity.Address;

public interface AddressService {

    Address handleAddress(AddressDTO addressDTO);
}
