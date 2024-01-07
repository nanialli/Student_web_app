package in.Student.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.Student.entity.StudentEnq;


public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer> {
  public List<StudentEnq> findByCounsellorId(Integer counsellorId);
  public List<StudentEnq> findByCounsellorIdAndStatus(Integer counsellorId, String status);

  @Query("SELECT p FROM StudentEnq p WHERE p.mode LIKE %:mode% AND p.course Like %:course% And status Like %:status% AND counsellorId= :cid")
  public List<StudentEnq> findBySearch (@Param("mode") String mode,@Param("course") String course , @Param("status") String status ,@Param("cid") Integer cid) ;
}
