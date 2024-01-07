package in.Student.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupDTO {
	@NotEmpty(message = "name not be empty")
	@Size(max = 50, message = "name can not be exeded 50 characters")
	String name;
	@Email(message = "enter valid email ")
	String email;
	@NotEmpty(message = "Password is required")
	@Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!_.-])[0-9a-zA-Z@#$%^&*!_.-]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	String password;
	@NotEmpty(message = "Phone No should not be empty")
	@Pattern(regexp = "[6-9][0-9]{9}", message = "Enter valid phone No")
	String phno;
}
