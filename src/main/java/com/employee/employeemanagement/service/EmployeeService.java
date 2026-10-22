
package com.employee.employeemanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.employee.employeemanagement.dto.EmployeeDTO;
import com.employee.employeemanagement.entity.Employee;
import com.employee.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getStatus() == null
                || employee.getStatus().isBlank()) {
            employee.setStatus("ACTIVE");
        }

        validateStatus(employee.getStatus());

        return employeeRepository.save(employee);
    }

    public List<Employee> createMultipleEmployees(
            List<Employee> employees) {
        employees.forEach(employee -> {
            if (employee.getStatus() == null
                    || employee.getStatus().isBlank()) {
                employee.setStatus("ACTIVE");
            }
            validateStatus(employee.getStatus());
        });

        return employeeRepository.saveAll(employees);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Page<EmployeeDTO> searchEmployees(
            String search, String status, Pageable pageable) {

        if (status != null && !status.isBlank()) {
            validateStatus(status);
        }

        return employeeRepository
                .searchEmployees(search, status, pageable)
                .map(this::convertToDTO);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found with ID: " + id));
    }

    public Employee updateEmployee(
            Long id, Employee employeeDetails) {

        Employee employee = getEmployeeById(id);

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());

        if (employeeDetails.getStatus() != null
                && !employeeDetails.getStatus().isBlank()) {
            validateStatus(employeeDetails.getStatus());
            employee.setStatus(employeeDetails.getStatus());
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getStatus());
    }

    private void validateStatus(String status) {
        if (!"ACTIVE".equalsIgnoreCase(status)
                && !"INACTIVE".equalsIgnoreCase(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status must be ACTIVE or INACTIVE");
        }
    }
}
