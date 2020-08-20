package com.configuration.machine.mock;

import com.configuration.machine.controllers.OwnerController;
import com.configuration.machine.models.Owner;
import com.configuration.machine.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerMockTests {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    @Test
    void getAllOwnersTest(){
        Mockito.when(ownerService.getAllOwners()).thenReturn(findAllMock());
        List<Owner> owners = ownerController.getAllOwners();

        assertTrue(owners != null);
        assertEquals(2, owners.size());
    }

    @Test
    void createOwnerTest(){
        Owner owner = createOwnerMock();

        Mockito.when(ownerService.createOwner(owner)).thenReturn(owner);
        ResponseEntity responseEntity = ownerController.createOwner(owner);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getOwnerByIdTest(){
        Owner owner = createOwnerMock();
        long id = 1;

        Mockito.when(ownerService.getOwnerById(id)).thenReturn(owner);
        ResponseEntity responseEntity = ownerController.getOwnerById(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    @Test
    void deleteOwnerById(){
        long id = 1;

        Mockito.when(ownerService.deleteOwnerById(id)).thenReturn(true);
        ResponseEntity responseEntity = ownerController.deleteOwnerById(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    private List<Owner> findAllMock(){
        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner());
        owners.add(new Owner());

        return owners;
    }

    private Owner createOwnerMock(){
        Owner owner = new Owner();
        owner.setFirstName("yyy");
        owner.setLastName("xxx");
        owner.setEmial("123@gmail.com");
        owner.setPhoneNumber("123123123");
        owner.setId((long) 1);

        return owner;
    }

}
