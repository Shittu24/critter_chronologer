package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ScheduleService {
    Schedule createSchedule(List<Long> employeeIds, List<Long> petIds, LocalDate date, Set<EmployeeSkill> activities);
    List<Schedule> getAllSchedules();
    List<Schedule> getScheduleForPet(long petId);
    List<Schedule> getScheduleForEmployee(long employeeId);
    List<Schedule> getScheduleForCustomer(long customerId);
}
