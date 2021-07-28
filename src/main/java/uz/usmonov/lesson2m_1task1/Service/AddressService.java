package uz.usmonov.lesson2m_1task1.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.usmonov.lesson2m_1task1.Repository.AddressRepository;
import uz.usmonov.lesson2m_1task1.entity.Address;
import uz.usmonov.lesson2m_1task1.payload.AddressDto;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse add(AddressDto addressDto){
        Address address=new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        Address save = addressRepository.save(address);
        return new ApiResponse("Address added",true);
    }

    public ApiResponse get(Integer id){
        boolean exists = addressRepository.existsById(id);
        if (exists){
            Address address = addressRepository.getById(id);
            return new ApiResponse("Done",true,address);
        }
        return new ApiResponse("Address not found",false);
    }

    public ApiResponse edit(AddressDto addressDto,Integer id){
        boolean exists = addressRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Address not found",false);
        }
        Address address = addressRepository.getById(id);
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        Address save = addressRepository.save(address);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id){
        boolean exists = addressRepository.existsById(id);
        if (!exists){
            return new ApiResponse("Address not found",false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }

}
