package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.ObjectNotFoundException;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressesService {
    @Autowired
    AddressRepository addressRepository;
    public List<Addresses> findAll(UUID customerId) {
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
        verifyAddress(address);
        existingAddres.setCep(address.getCep());
        existingAddres.setCity(address.getCity());
        existingAddres.setState(address.getState());
        existingAddres.setLandmark(address.getLandmark());
        existingAddres.setCountry(address.getCountry());
        return addressRepository.save(existingAddres);


    }

    private void verifyAddress(Addresses addresses) {
        if (addresses == null) {
            throw new IllegalArgumentException("Endereço nulo");
        }
        if (addresses.getCep() == null) {
            throw new IllegalArgumentException("cep é obrigatorio");
        }
        if (addresses.getCity() == null) {
            throw new IllegalArgumentException("Cidade é obrigatorio");
        }
        if (addresses.getCountry() == null) {
            throw new IllegalArgumentException("País é obrigatorio");
        }
        if (addresses.getState() == null) {
            throw new IllegalArgumentException("Estado é obrigatorio");
        }
        if (addresses.getLandmark() == null) {
            throw new IllegalArgumentException("Ponto de referencia é nulo");
        }
        if (addresses.getNeighborhood() == null) {
            throw new IllegalArgumentException("Vizinhança é nula");
        }

    }

}
