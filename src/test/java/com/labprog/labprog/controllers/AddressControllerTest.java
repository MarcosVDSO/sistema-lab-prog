package com.labprog.labprog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.services.AddressesService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressControllerTest.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressesService addressesService;

    @InjectMocks
    private AddressesController addressController;

    private ObjectMapper objectMapper;
    private UUID testUuid;
    private Addresses testAddress;



}
