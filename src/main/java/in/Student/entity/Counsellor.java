package in.Student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "counsellor_info")
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer counsellorId ;
	String name ;
	String email ;
	String password ;
	String phno ;

}
