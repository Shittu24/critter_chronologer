package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setPhoneNumber("123456789");
        customer = customerRepository.save(customer);

        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setType(PetType.DOG);
        pet.setBirthDate(LocalDate.now());
        pet.setOwner(customer);
        pet = petRepository.save(pet);

        Employee employee = new Employee();
        employee.setName("Jane Doe");
        employee.setSkills(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING, EmployeeSkill.PETTING)));
        employee.setDaysAvailable(new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        employee = employeeRepository.save(employee);

        Schedule schedule = new Schedule();
        schedule.setDate(LocalDate.now());
        schedule.setActivities(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING)));
        schedule.setEmployees(Arrays.asList(employee));
        schedule.setPets(Arrays.asList(pet));
        scheduleRepository.save(schedule);
    }
}
