package com.tstcore.appinvestecclient;

import com.tstcore.appinvestecclient.entities.Client;
import com.tstcore.appinvestecclient.entities.PhysicalAddress;
import com.tstcore.appinvestecclient.repositories.ClientRepository;
import com.tstcore.appinvestecclient.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AppInvestecClientServiceTest {

    private long CLIENT_ID = 1L
        ,ADDRESS_ID = 1L;
    private String FULLNAME = "John"
            , LASTNAME = "Doe"
            ,UPDATED_LASTNAME= "Cena";
    private String ID_NUMBER = "8708126104082";
    private String MOBILE_NUMBER = "0829119110"
            ,UPDATE_MOBILE_NUMBER="0712345670";
    private String STREET_ADDRES = "";

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    void testClientServiceInjection(){
        assertNotNull(clientService);
    }

    @Test
    void testSave(){
        Client client = buildClient();
        when(clientRepository.save(client)).thenReturn(client);
        Client savedClient = clientService.save(client);
        assertNotNull(savedClient);
        assertEquals(savedClient.getId(),CLIENT_ID);

    }

    @Test
    void testFindAll(){
        Client client = buildClient();

        List<Client> clientList = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertEquals(1,clients.size());
    }

    @Test
    void testFindById(){
        Client client = buildClient();

        when(clientRepository.findById(CLIENT_ID)).thenReturn(Optional.of(client));

        Client savedClient = clientService.findOneById(CLIENT_ID);
        assertNotNull(savedClient);
        assertEquals(savedClient.getId(),CLIENT_ID);
    }

    @Test
    void testFindByFullName(){
        Client client = buildClient();

        List<Client> clientList = Arrays.asList(client);
        when(clientRepository.findByFullName(FULLNAME)).thenReturn(clientList);

        List<Client> clients = clientService.findByFullName(FULLNAME);
        assertNotNull(clients);
        assertEquals(1,clients.size());
    }

    @Test
    void testFindByMobileNumber(){
        Client client = buildClient();

        when(clientRepository.findByMobileNumber(MOBILE_NUMBER)).thenReturn(Optional.of(client));

        Client savedClient = clientService.findOneByMobileNumber(MOBILE_NUMBER);
        assertNotNull(savedClient);
        assertEquals(MOBILE_NUMBER,savedClient.getMobileNumber());
    }

    @Test
    void testFindByIdNumber(){
        Client client = buildClient();

        when(clientRepository.findByIdNumber(ID_NUMBER)).thenReturn(Optional.of(client));

        Client savedClient = clientService.findOneByIdNumber(ID_NUMBER);
        assertNotNull(savedClient);
        assertEquals(savedClient.getId(),CLIENT_ID);
        assertEquals(savedClient.getIdNumber(),ID_NUMBER);
    }

    @Test
    void testUpdateByClientAndId(){
        Client client = buildClient();

        when(clientRepository.save(client)).thenReturn(client);

        client.setMobileNumber(UPDATE_MOBILE_NUMBER);
        client.setLastName(UPDATED_LASTNAME);

        Client updatedClient = clientService.update(client,CLIENT_ID);
        assertNotNull(updatedClient);
        assertEquals(UPDATED_LASTNAME,updatedClient.getLastName());
        assertEquals(UPDATE_MOBILE_NUMBER,updatedClient.getMobileNumber());
    }

    @Test
    void testUpdateByCLient(){
        Client client = buildClient();

        when(clientRepository.save(client)).thenReturn(client);

        client.setMobileNumber(UPDATE_MOBILE_NUMBER);
        client.setLastName(UPDATED_LASTNAME);

        Client updatedClient = clientService.update(client);
        assertNotNull(updatedClient);
        assertEquals(CLIENT_ID, updatedClient.getId());
        assertEquals(UPDATED_LASTNAME,updatedClient.getLastName());
        assertEquals(UPDATE_MOBILE_NUMBER,updatedClient.getMobileNumber());
    }

    Client buildClient(){
            Client client = new Client();
            client.setId(CLIENT_ID);
            client.setFullName(FULLNAME);
            client.setLastName(LASTNAME);
            client.setIdNumber(ID_NUMBER);
            client.setMobileNumber(MOBILE_NUMBER);
            client.setAddress(buildAddress());

        return client;
    }

    PhysicalAddress buildAddress(){

        PhysicalAddress address = new PhysicalAddress();
        address.setStreetAddress("100 Harry Galaun DR");
        address.setId(ADDRESS_ID);
        address.setLineOne("");
        address.setLineTwo("Vorna village");
        address.setSuburb("Vorna Valley");
        address.setCity("Midrand");
        address.setAreaCode("1686");
        return address;
    }

}
