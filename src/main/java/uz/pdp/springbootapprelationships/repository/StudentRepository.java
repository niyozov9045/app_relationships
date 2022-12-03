package uz.pdp.springbootapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootapprelationships.entity.Group;
import uz.pdp.springbootapprelationships.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByGroup_Faculty_Univercity_Id(Integer group_faculty_univercity_id);




}
