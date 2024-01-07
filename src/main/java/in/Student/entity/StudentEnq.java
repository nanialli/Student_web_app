package in.Student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "student_enq_info")
public class StudentEnq {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer enquiryId ;
	@NotEmpty(message = "name is requiried ")
	String name ;
	
	@Pattern(regexp = "[6-9][0-9]{9}" ,message = "Enter valid phone no ")
	String phno ;
	@NotEmpty(message = "mode is requiried ")
	String mode ;
	@NotEmpty(message = "course is requiried ")
	String course ;
	@NotEmpty(message = "status is requiried ")
	String status ;
	Integer counsellorId ;
	

}
