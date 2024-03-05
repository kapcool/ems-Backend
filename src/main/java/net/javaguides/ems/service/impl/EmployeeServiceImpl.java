package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployee(Long empId) {
        Employee fetchedEmployee = employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("employee does not exist" + empId));
        return EmployeeMapper.mapToEmployeeDto(fetchedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allFetchedEmployees = employeeRepository.findAll();
        return allFetchedEmployees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto employeeDto) {
        Employee fetchedEmployee = employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("employee does not exist" + empId));
        fetchedEmployee.setFirstName(employeeDto.getFirstName());
        fetchedEmployee.setLastName(employeeDto.getLastName());
        fetchedEmployee.setEmail(employeeDto.getEmail());
        Employee savedEmployee = employeeRepository.save(fetchedEmployee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {
        Employee fetchedEmployee = employeeRepository.findById(empId)
                .orElseThrow(()-> new ResourceNotFoundException("employee does not exist" + empId));
        employeeRepository.deleteById(empId);
    }


}
