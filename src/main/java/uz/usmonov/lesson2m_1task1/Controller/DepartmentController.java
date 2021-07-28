package uz.usmonov.lesson2m_1task1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson2m_1task1.Service.DepartmentService;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.DepartmentDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse add = departmentService.add(departmentDto);
        return ResponseEntity.status(add.isSuccess()? 201:409).body(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid @RequestBody DepartmentDto departmentDto,@PathVariable Integer id){
        ApiResponse edit = departmentService.edit(departmentDto, id);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse delete = departmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?202:409).body(delete);
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
