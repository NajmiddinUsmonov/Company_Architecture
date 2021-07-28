package uz.usmonov.lesson2m_1task1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.usmonov.lesson2m_1task1.Repository.AddressRepository;
import uz.usmonov.lesson2m_1task1.Repository.CompanyRepository;
import uz.usmonov.lesson2m_1task1.entity.Company;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.CompanyDto;

import javax.validation.Valid;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse add(@Valid @RequestBody CompanyDto companyDto){
        boolean exists = addressRepository.existsById(companyDto.getAddressId());
        if (!exists){
            return new ApiResponse("Address not found",false);
        }
        Company company=new Company();
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        company.setAddress(addressRepository.getById(companyDto.getAddressId()));
        Company save = companyRepository.save(company);
        return new ApiResponse("Added",true);
    }

    public ApiResponse get(Integer id){
        boolean exists = companyRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Company not found",false);
        }
        Company company = companyRepository.getById(id);
        return new ApiResponse("Done",true,company);
    }

    public ApiResponse edit(CompanyDto companyDto,Integer id){
        boolean exists = companyRepository.existsById(id);
        boolean exists1 = addressRepository.existsById(companyDto.getAddressId());
        if (!exists1){
            return new ApiResponse("Address not found",false);
        }
        if (!exists){
            return new ApiResponse("Company not found",false);
        }
        Company company = companyRepository.getById(id);
        company.setAddress(addressRepository.getById(companyDto.getAddressId()));
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Edited",true);
    }
    public ApiResponse delete(Integer id){
        boolean exists = companyRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Company not found",false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
