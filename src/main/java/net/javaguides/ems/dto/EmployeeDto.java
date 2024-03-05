package net.javaguides.ems.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
