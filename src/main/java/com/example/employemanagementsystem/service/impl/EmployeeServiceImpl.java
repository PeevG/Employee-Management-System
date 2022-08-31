package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.repository.DepartmentRepository;
import com.example.employemanagementsystem.repository.EmployeeRepository;
import com.example.employemanagementsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EmployeeGetAllBindingModel> showAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(this::mapEmployeeEntityToBindingModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(EmployeeAddBindingModel employeeAddBindingModel) {
        EmployeeEntity employee = modelMapper.map(employeeAddBindingModel, EmployeeEntity.class);
        DepartmentEntity department = departmentRepository
                .findByName(employeeAddBindingModel.getDepartment()).orElseThrow(()
                        -> new ObjectNotFoundException("Department with name " + employeeAddBindingModel.getDepartment() + " is not found."));
        employee.setDepartment(department);
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
        DepartmentEntity department = departmentRepository.findByName(employeeUpdateBindingModel.getDepartment()).get();

        employee.setFirstName(employeeUpdateBindingModel.getFirstName())
                .setLastName(employeeUpdateBindingModel.getLastName())
                .setAge(employeeUpdateBindingModel.getAge())
                .setEmail(employeeUpdateBindingModel.getEmail())
                .setDepartment(department)
                .setId(employeeUpdateBindingModel.getId());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public Page<EmployeeGetAllBindingModel> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return employeeRepository.findAll(pageable)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeGetAllBindingModel.class));

    }

    @Override
    public void seedEmployees() {
        if (employeeRepository.count() < 1) {
            EmployeeEntity ivo = new EmployeeEntity().setFirstName("Ivailo").setLastName("Ivailov")
                    .setAge(21).setEmail("ivo@gmail.com").setDepartment(departmentRepository.getReferenceById(1L));

            EmployeeEntity desi = new EmployeeEntity().setFirstName("Desislava").setLastName("Stoianova")
                    .setAge(27).setEmail("desi@gmail.com").setDepartment(departmentRepository.getReferenceById(2L));

            EmployeeEntity pesho = new EmployeeEntity().setFirstName("Pesho").setLastName("Petrov")
                    .setAge(32).setEmail("petyr@gmail.com").setDepartment(departmentRepository.getReferenceById(3L));

            EmployeeEntity sasho = new EmployeeEntity().setFirstName("Aleksandyr").setLastName("Dimitrov")
                    .setAge(29).setEmail("sashik@gmail.com").setDepartment(departmentRepository.getReferenceById(1L));

            EmployeeEntity gosho = new EmployeeEntity().setFirstName("Georgi").setLastName("Metodiev")
                    .setAge(31).setEmail("joro@gmail.com").setDepartment(departmentRepository.getReferenceById(1L));
            employeeRepository.saveAll(List.of(ivo, desi, pesho, sasho, gosho));
        }
    }

    EmployeeGetAllBindingModel mapEmployeeEntityToBindingModel(EmployeeEntity employee) {
        EmployeeGetAllBindingModel model = modelMapper.map(employee, EmployeeGetAllBindingModel.class);
        model.setDepartment(employee.getDepartment().getName());
        return model;
    }
}
