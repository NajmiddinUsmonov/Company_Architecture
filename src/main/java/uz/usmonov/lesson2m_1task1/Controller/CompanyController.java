package uz.usmonov.lesson2m_1task1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson2m_1task1.Service.CompanyService;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.CompanyDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse add = companyService.add(companyDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @RequestBody CompanyDto companyDto,@PathVariable Integer id){
        ApiResponse edit = companyService.edit(companyDto, id);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse delete = companyService.delete(id);
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
