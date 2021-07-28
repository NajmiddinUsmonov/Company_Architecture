package uz.usmonov.lesson2m_1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {

    @NotNull(message = "CorpName should not be empty")
    private String corpName;

    @NotNull(message = "Director's name should not be empty")
    private String  directorName;

    @NotNull(message = "Address's Id should not be empty")
    private Integer addressId;
}
