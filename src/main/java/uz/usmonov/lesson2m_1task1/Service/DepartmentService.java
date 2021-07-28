package uz.usmonov.lesson2m_1task1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.usmonov.lesson2m_1task1.Repository.CompanyRepository;
import uz.usmonov.lesson2m_1task1.Repository.DepartmentRepository;
import uz.usmonov.lesson2m_1task1.entity.Department;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.DepartmentDto;

import java.util.HashSet;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(DepartmentDto departmentDto){

        for (Integer companyId : departmentDto.getCompanyIds()) {

            boolean exists = companyRepository.existsById(companyId);
            if (!exists){
                return new ApiResponse("Company not found",false,"Company's Id="+companyId);
            }
        }
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(new HashSet<>(companyRepository.findAllById(departmentDto.getCompanyIds())));
        departmentRepository.save(department);
        return new ApiResponse("Added",true);
    }
    public ApiResponse get(Integer id){
        boolean exists = departmentRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Department not found",false);
        }
        Department department = departmentRepository.getById(id);
        return new ApiResponse("Done",true,department);
    }

    public ApiResponse edit(DepartmentDto departmentDto,Integer id){
        for (Integer companyId : departmentDto.getCompanyIds()) {

            boolean exists = companyRepository.existsById(companyId);
            if (!exists){
                return new ApiResponse("Company not found",false,"Company's Id="+companyId);
            }
        }

        Department department = departmentRepository.getById(id);
        department.setName(departmentDto.getName());
        department.setCompany(new HashSet<>(companyRepository.findAllById(departmentDto.getCompanyIds())));
        departmentRepository.save(department);
        return new ApiResponse("Edit",true);
    }

    public ApiResponse delete(Integer id){
        boolean exists = departmentRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Department not found",false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
