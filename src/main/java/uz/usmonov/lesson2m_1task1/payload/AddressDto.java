package uz.usmonov.lesson2m_1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {


    @NotNull(message = "Street should not be empty")
    private String street;

    @NotNull(message = "Home Number should not be empty")
    private String  homeNumber;
}
