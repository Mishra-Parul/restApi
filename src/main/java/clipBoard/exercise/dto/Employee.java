package clipBoard.exercise.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private UUID employeeId;
    @NotBlank
    private String name;
    @NotNull
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$")
    private String salary;
    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;
    @NotBlank
    private String department;
    @NotBlank
    private String subDepartment;
    private Boolean onContract = false;

}