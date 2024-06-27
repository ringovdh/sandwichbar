package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.AddressDTO;
import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.mapper.AddressMapper;
import be.faros.sandwichbar.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address handleAddress(AddressDTO addressDTO) {
        Address address = null;
        if (addressDTO != null) {
            address = addressRepository.save(AddressMapper.mapAddressDTOToAddress(addressDTO));
        }
        return address;
    }
}
