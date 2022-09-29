package com.tstcore.appinvestecclient.controllers;

import com.tstcore.appinvestecclient.entities.Client;
import com.tstcore.appinvestecclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){

        List<Client> clients = clientService.findAll();
        if(clients.isEmpty() || clients.equals(null)){
            return clients.isEmpty() ? new ResponseEntity<List<Client>>(clients, HttpStatus.OK):
                    new ResponseEntity<List<Client>>(clients, HttpStatus.NOT_FOUND);
        }
        else{ return new ResponseEntity<List<Client>>(clients, HttpStatus.OK); }
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {

        Client savedClient = clientService.save(client);
        if(savedClient.equals(null)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<Client>(savedClient, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(clientService.findOneById(id),HttpStatus.OK);
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<List<Client>> findByFullName(@PathVariable("fullName") String fullName) {
        return new ResponseEntity<>(clientService.findByFullName(fullName),HttpStatus.OK);
    }

    @GetMapping("/idnumber/{idNumber}")
    public ResponseEntity<Client> findByIdNumber(@PathVariable("idNumber") String idNumber) {
        return new ResponseEntity<>(clientService.findOneByIdNumber(idNumber),HttpStatus.OK);
    }

    @GetMapping("/mobilenumber/{mobileNumber}")
    public ResponseEntity<Client> findByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) {
        return new ResponseEntity<>(clientService.findOneByMobileNumber(mobileNumber),HttpStatus.OK);
    }

}
