package uz.usmonov.lesson2m_1task1.payload;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

    @NotNull(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Phone number should not be empty")
    private String phoneNumber;

    @NotNull(message = "Address's ID should not be empty")
    private Integer addressId;

    @NotNull(message = "Department's ID should not be empty")
    private Integer departmentId;
}
