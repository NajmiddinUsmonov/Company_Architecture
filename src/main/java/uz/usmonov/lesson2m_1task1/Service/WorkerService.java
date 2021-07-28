package uz.usmonov.lesson2m_1task1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.usmonov.lesson2m_1task1.Repository.AddressRepository;
import uz.usmonov.lesson2m_1task1.Repository.DepartmentRepository;
import uz.usmonov.lesson2m_1task1.Repository.WorkerRepository;
import uz.usmonov.lesson2m_1task1.entity.Worker;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.WorkerDto;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse add(WorkerDto workerDto){
        boolean address = addressRepository.existsById(workerDto.getAddressId());
        boolean department = departmentRepository.existsById(workerDto.getDepartmentId());
        if (!address){
            return new ApiResponse("Address not found",false);
        }
        if (!department){
            return new ApiResponse("Department not found",false);
        }
        Worker worker=new Worker();
        worker.setAddress(addressRepository.getById(workerDto.getAddressId()));
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(departmentRepository.getById(workerDto.getDepartmentId()));
        workerRepository.save(worker);
        return new ApiResponse("Addes",true);
    }

    public ApiResponse get(Integer id){
        boolean worker = workerRepository.existsById(id);
        if (!worker){
            return new ApiResponse("Worker not found",false);
        }
        Worker worker1 = workerRepository.getById(id);
        return new ApiResponse("Done",true,worker1);
    }
    public ApiResponse edit(WorkerDto workerDto,Integer id){
        boolean worker = workerRepository.existsById(id);
        boolean address = addressRepository.existsById(workerDto.getAddressId());
        boolean department = departmentRepository.existsById(workerDto.getDepartmentId());
        boolean phoneNumber = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (phoneNumber){
            return new ApiResponse("Phone Number already exist",false);
        }
        if (!address){
            return new ApiResponse("Address not found",false);
        }
        if (!department){
            return new ApiResponse("Department not found",false);
        }
        if (!worker){
            return new ApiResponse("Worker not found",false);
        }
        Worker worker1 = workerRepository.getById(id);
        worker1.setName(workerDto.getName());
        worker1.setAddress(addressRepository.getById(workerDto.getAddressId()));
        worker1.setDepartment(departmentRepository.getById(workerDto.getDepartmentId()));
        worker1.setPhoneNumber(workerDto.getPhoneNumber());//shart berish

        Worker save = workerRepository.save(worker1);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id){
        boolean exists = workerRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Worker not found",false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
    
}
