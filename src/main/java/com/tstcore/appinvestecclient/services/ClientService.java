package com.tstcore.appinvestecclient.services;

import com.tstcore.appinvestecclient.entities.Client;
import com.tstcore.appinvestecclient.entities.PhysicalAddress;
import com.tstcore.appinvestecclient.exception.ClientException;
import com.tstcore.appinvestecclient.repositories.ClientRepository;
import com.tstcore.appinvestecclient.repositories.PhysicalAddressRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IService<Client> {

    private ClientRepository clientRepository;
    private PhysicalAddressRepository physicalAddressRepository;

    public ClientService(ClientRepository clientRepository,PhysicalAddressRepository physicalAddressRepository){
        this.clientRepository = clientRepository;
        this.physicalAddressRepository = physicalAddressRepository;
    }
    /**
     * @return
     */
    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Client findOneById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("Could not find client with id=", id));
    }

    /**
     * @param fullName
     * @return
     */
    public List<Client> findByFullName(String fullName) {
        return clientRepository.findByFullName(fullName);
    }

    /**
     * @param mobileNumber a unique mobile number to find the client
     * @return
     */
    public Client findOneByMobileNumber(String mobileNumber) {
        return clientRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ClientException("Could not find client with mobile number=", mobileNumber));
    }

    /**
     * @param idNumber a unique id number to find the client
     * @return Client object if available
     */
    public Client findOneByIdNumber(String idNumber) {
        return clientRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new ClientException("Could not find client with IdNumber=", idNumber));
    }

    /**
     * @param client a client object to save to the database
     * @return client object with a newly generated unique id
     */
    @Override
    public Client save(Client client) {
        PhysicalAddress address = client.getAddress();
        address.setClient(client);
        PhysicalAddress savedAddress = physicalAddressRepository.save(address);
        client.setAddress(savedAddress);
        return Optional.of(this.clientRepository.save(client))
                .orElseThrow(() -> new ClientException("Client with Id_Number="+client.getIdNumber()+" already exists!"));
    }

    /**
     * @param client
     * @param id
     * @return
     */
    @Override
    public Client update(Client client, long id) {
        return clientRepository.findById(id).map((c) -> {
            c.setFullName(client.getFullName());
            c.setLastName(client.getLastName());
            c.setIdNumber(client.getIdNumber());
            c.setMobileNumber(client.getMobileNumber());
            return clientRepository.save(c);
        }).orElseGet(() -> {
            client.setId(id);
            return clientRepository.save(client);
        });
    }

    /**
     * @param client
     * @return
     */
    @Override
    public Client update(Client client) {
        PhysicalAddress address = client.getAddress();
        address.setClient(client);
        PhysicalAddress updatedAddress = physicalAddressRepository.save(address);
        client.setAddress(updatedAddress);
        return clientRepository.save(client);
    }

    /**
     * @param id an id to delete a client entity by an id in the database
     */
    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }

    /**
     * @param client a client object to remove from the database
     */
    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
