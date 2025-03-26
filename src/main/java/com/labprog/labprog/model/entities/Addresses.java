package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labprog.labprog.DTO.AddressesDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Addresses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "landmark", nullable = false)
    private String landmark;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    public Addresses(AddressesDTO addressesDTO) {
        this.country = addressesDTO.getCountry();
        this.state = addressesDTO.getState();
        this.landmark = addressesDTO.getLandmark();
        this.city = addressesDTO.getCity();
        this.cep = addressesDTO.getCep();
        this.user = addressesDTO.getUser();
        this.addressId = addressesDTO.getAddressId();
        this.street = addressesDTO.getStreet();
        this.number = addressesDTO.getNumber();
        this.neighborhood = addressesDTO.getNeighborhood();
    }
}
