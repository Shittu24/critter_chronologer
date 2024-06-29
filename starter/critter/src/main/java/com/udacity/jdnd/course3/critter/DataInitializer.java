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
        customer.setName("InitJohn Doe");
        customer.setPhoneNumber("987654321");
        customer = customerRepository.save(customer);

        Customer customer2 = new Customer();
        customer2.setName("InitEllis Doe");
        customer2.setPhoneNumber("445667665");
        customer2 = customerRepository.save(customer2);

        Pet pet = new Pet();
        pet.setName("InitBuddy");
        pet.setType(PetType.DOG);
        pet.setBirthDate(LocalDate.now());
        pet.setOwner(customer);
        pet = petRepository.save(pet);

        Pet pet2 = new Pet();
        pet2.setName("InitTobs");
        pet2.setType(PetType.DOG);
        pet2.setBirthDate(LocalDate.now());
        pet2.setOwner(customer);
        pet2 = petRepository.save(pet2);

        Employee employee = new Employee();
        employee.setName("InitJane Doe");
        employee.setSkills(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING, EmployeeSkill.PETTING)));
        employee.setDaysAvailable(new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        employee = employeeRepository.save(employee);

        Employee employee2 = new Employee();
        employee2.setName("InitMark Doe");
        employee2.setSkills(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING, EmployeeSkill.PETTING)));
        employee2.setDaysAvailable(new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        employee2 = employeeRepository.save(employee2);

        Schedule schedule = new Schedule();
        schedule.setDate(LocalDate.now());
        schedule.setActivities(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING)));
        schedule.setEmployees(Arrays.asList(employee));
        schedule.setPets(Arrays.asList(pet));
        scheduleRepository.save(schedule);

        Schedule schedule2 = new Schedule();
        schedule2.setDate(LocalDate.now());
        schedule2.setActivities(new HashSet<>(Arrays.asList(EmployeeSkill.FEEDING)));
        schedule2.setEmployees(Arrays.asList(employee));
        schedule2.setPets(Arrays.asList(pet));
        scheduleRepository.save(schedule2);
    }
}
