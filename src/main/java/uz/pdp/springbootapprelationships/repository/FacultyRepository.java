package uz.pdp.springbootapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootapprelationships.entity.Address;
import uz.pdp.springbootapprelationships.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    boolean existsByNameAndUnivercityId(String name, Integer univercity_id);

    List<Faculty> findAllByUnivercityId(Integer univercity_id);
}
