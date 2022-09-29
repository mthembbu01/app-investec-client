package com.tstcore.appinvestecclient;

import com.tstcore.appinvestecclient.repositories.ClientRepository;
import com.tstcore.appinvestecclient.repositories.PhysicalAddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppInvestecClientApplicationTests {

	@Autowired
	private ClientRepository clientRepository;

    @Autowired
    private PhysicalAddressRepository physicalAddressRepository;

	@Test
	void testClientRepositoryInjection() {
		assertNotNull(clientRepository);
        assertNotNull(physicalAddressRepository);
	}

}
