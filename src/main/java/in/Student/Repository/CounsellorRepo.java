package in.Student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.Student.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	public Optional<Counsellor> findByEmail(String email);

	@Query("select c.counsellorId from Counsellor c where c.email = ?1 and c.password = ?2")
	public Integer findByEmailAndPassword(String email, String password);
	
	@Query("select r.password from Counsellor r where r.email = :email")
	public String findbyEmail(String email) ;
}
