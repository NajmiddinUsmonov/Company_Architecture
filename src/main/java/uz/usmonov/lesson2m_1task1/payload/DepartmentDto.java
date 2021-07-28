package uz.usmonov.lesson2m_1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class DepartmentDto {



    @NotNull(message = "Name should not be empty")
    private String name;

    @NotNull(message = "ID should not be empty")
    private Set<Integer> companyIds;
}
