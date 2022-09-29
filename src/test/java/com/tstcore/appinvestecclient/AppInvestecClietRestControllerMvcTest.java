package com.tstcore.appinvestecclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hazelcast.com.google.common.net.MediaType;
import com.tstcore.appinvestecclient.entities.Client;
import com.tstcore.appinvestecclient.entities.PhysicalAddress;
import com.tstcore.appinvestecclient.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppInvestecClietRestControllerMvcTest {

    private static final long CLIENT_ID = 1L
            ,ADDRESS_ID = 1L;
    private static final String FULLNAME = "John"
            , LASTNAME = "Doe"
            ,UPDATED_LASTNAME= "Cena"
            ,CONTEXT_URL = "/client-api"
            ,CLIENT_URL = "/client-api/client/";
    private static final String MOBILE_NUMBER = "0829119110"
            ,ID_NUMBER = "8708126104082"
            ,UPDATE_MOBILE_NUMBER="0712345670"
            ,STREET_ADDRESS = "100 Harry Galaun DR";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testFindAll() throws Exception {
        Client client = buildClient();

        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clients);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(CLIENT_URL)
                .contextPath(CONTEXT_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(clients)));

    }

    @Test
    public void testSave() throws Exception {
        Client client = buildClient();
        when(clientRepository.save(any())).thenReturn(client);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(post(CLIENT_URL)
                        .contextPath(CONTEXT_URL)
                        .contentType(String.valueOf(MediaType.JSON_UTF_8))
                        .content(objectWriter.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(client)));
    }

    @Test
    public void testFindById() throws Exception {
        Client client = buildClient();
        when(clientRepository.findById(CLIENT_ID)).thenReturn(Optional.of(client));
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(CLIENT_URL+CLIENT_ID)
                        .contextPath(CONTEXT_URL)
                        .contentType(String.valueOf(MediaType.JSON_UTF_8))
                        .content(objectWriter.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(client)));
    }

    @Test
    public void testFindByFullname() throws Exception {
        Client client = buildClient();
        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findByFullName(FULLNAME)).thenReturn(clients);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(CLIENT_URL+"fullname/"+FULLNAME)
                        .contextPath(CONTEXT_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(clients)));
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
        address.setStreetAddress(STREET_ADDRESS);
        address.setId(ADDRESS_ID);
        address.setLineOne("");
        address.setLineTwo("Vorna village");
        address.setSuburb("Vorna Valley");
        address.setCity("Midrand");
        address.setAreaCode("1686");
        return address;
    }
}
