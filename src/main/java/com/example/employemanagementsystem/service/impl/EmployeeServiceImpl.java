package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.repository.EmployeeRepository;
import com.example.employemanagementsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeGetAllBindingModel> showAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeGetAllBindingModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(@Valid EmployeeAddBindingModel employeeAddBindingModel) {
        EmployeeEntity employee = modelMapper.map(employeeAddBindingModel, EmployeeEntity.class);
        this.employeeRepository.save(employee);
    }

    @Override
    public EmployeeUpdateBindingModel getEmployeeById(long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Employee with " + id + " is not found."));
        return modelMapper.map(employee, EmployeeUpdateBindingModel.class);
    }


    @Override
    public void updateEmployee(EmployeeUpdateBindingModel employeeUpdateBindingModel) {

        EmployeeUpdateBindingModel employeeById = getEmployeeById(employeeUpdateBindingModel.getId());
        EmployeeEntity employee = modelMapper.map(employeeById, EmployeeEntity.class);

        employee.setFirstName(employeeUpdateBindingModel.getFirstName())
                .setLastName(employeeUpdateBindingModel.getLastName())
                .setAge(employeeUpdateBindingModel.getAge())
                .setEmail(employeeUpdateBindingModel.getEmail());

        employeeRepository.save(employee);
    }
}
