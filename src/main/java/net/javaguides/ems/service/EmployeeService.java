package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployee(Long empId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long empId, EmployeeDto employeeDto);
    void deleteEmployee(Long empId);
}
