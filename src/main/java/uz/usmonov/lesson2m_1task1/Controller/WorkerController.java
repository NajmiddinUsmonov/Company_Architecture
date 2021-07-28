package uz.usmonov.lesson2m_1task1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson2m_1task1.Service.WorkerService;
import uz.usmonov.lesson2m_1task1.payload.ApiResponse;
import uz.usmonov.lesson2m_1task1.payload.WorkerDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping
    public ResponseEntity<ApiResponse> add( @Valid @RequestBody WorkerDto workerDto){
        ApiResponse add = workerService.add(workerDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable  Integer id){
        ApiResponse apiResponse = workerService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody WorkerDto workerDto,@PathVariable Integer id){
        ApiResponse edit = workerService.edit(workerDto, id);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse delete = workerService.delete(id);
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
