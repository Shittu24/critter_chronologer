package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);

        // Update the owner with the new pet
        Customer owner = savedPet.getOwner();
        if (owner != null) {
            List<Pet> ownerPets = owner.getPets();
            if (ownerPets == null) {
                ownerPets = new ArrayList<>();
                owner.setPets(ownerPets);
            }
            if (!ownerPets.contains(savedPet)) {
                ownerPets.add(savedPet);
                customerRepository.save(owner);
            }
        }
        return savedPet;
    }

    @Override
    public Pet getPet(long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
