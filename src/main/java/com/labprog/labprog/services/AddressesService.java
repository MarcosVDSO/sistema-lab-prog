package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.ObjectNotFoundException;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressesService {
    @Autowired
    AddressRepository addressRepository;
    public List<Addresses> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Addresses> findById(UUID id) {
        return addressRepository.findById(id);
    }

    public Addresses save(Addresses address) {
        verifyAddress(address);

        return addressRepository.save(address);
    }

    public void deleteById(UUID id) {
        addressRepository.deleteById(id);
    }

    public Addresses update(UUID id, Addresses address) {
        Addresses existingAddres = addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address Not Found"));

        if (address.getCep() != null) {
            existingAddres.setCep(address.getCep());
        }

        if (address.getCity() != null) {
            existingAddres.setCity(address.getCity());
        }

        if (address.getState() != null) {
            existingAddres.setState(address.getState());
        }

        if (address.getLandmark() != null) {
            existingAddres.setLandmark(address.getLandmark());
        }

        if (address.getCountry() != null) {
            existingAddres.setCountry(address.getCountry());
        }

        return addressRepository.save(existingAddres);

    }

    private void verifyAddress(Addresses addresses) {
        if (addresses == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (addresses.getCep() == null || addresses.getCep().isBlank()) {
            throw new IllegalArgumentException("CEP cannot be null or empty");
        }
        if (addresses.getCity() == null || addresses.getCity().isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (addresses.getCountry() == null || addresses.getCountry().isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (addresses.getState() == null || addresses.getState().isBlank()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }
        if (addresses.getLandmark() == null || addresses.getLandmark().isBlank()) {
            throw new IllegalArgumentException("Landmark cannot be null or empty");
        }
        if (addresses.getNeighborhood() == null || addresses.getNeighborhood().isBlank()) {
            throw new IllegalArgumentException("Neighborhood cannot be null or empty");
        }

        if (addresses.getStreet() == null || addresses.getStreet().isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }

    }

}
