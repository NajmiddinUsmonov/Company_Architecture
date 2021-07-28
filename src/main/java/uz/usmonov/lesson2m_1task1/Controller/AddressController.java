package uz.usmonov.lesson2m_1task1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson2m_1task1.Service.AddressService;
import uz.usmonov.lesson2m_1task1.payload.AddressDto;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody AddressDto addressDto){
        ApiResponse add = addressService.add(addressDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@Valid @RequestBody AddressDto addressDto,@PathVariable Integer id){
        ApiResponse edit = addressService.edit(addressDto, id);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse delete = addressService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
