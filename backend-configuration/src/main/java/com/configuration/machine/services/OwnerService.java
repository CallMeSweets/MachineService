package com.configuration.machine.services;

import com.configuration.machine.dao.OwnerRepository;
import com.configuration.machine.exceptions.OwnerNotFoundException;
import com.configuration.machine.models.Owner;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Log4j2
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    public List<Owner> getAllOwners(){
        log.trace("load all owners from db");
        return ownerRepository.findAll();
    }

    public Owner createOwner(Owner owner){
        log.trace("save new owner to db");
        return ownerRepository.save(owner);
    }

    public Boolean deleteOwnerById(Long id){
        log.trace("delete owner by id from db");
        ownerRepository.deleteById(id);
        log.debug("Owner deleted id: " + id);
        return true;
    }

    public Owner getOwnerById(Long id) throws NoSuchElementException{
        log.trace("load owner by id from db");
        Owner owner;
        try{
           owner = ownerRepository.findById(id).get();
            log.trace("Owner founded, id: " + id);
        }catch (NoSuchElementException e) {
            throw new OwnerNotFoundException("USER not found, ID: " + id);
        }
        return owner;
    }

}
