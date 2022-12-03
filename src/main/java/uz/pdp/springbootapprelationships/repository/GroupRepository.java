package uz.pdp.springbootapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootapprelationships.entity.Address;
import uz.pdp.springbootapprelationships.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculty_Univercity_Id(Integer faculty_univercity_id);




}
