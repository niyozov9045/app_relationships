package uz.pdp.springbootapprelationships.controller;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapprelationships.entity.Faculty;
import uz.pdp.springbootapprelationships.entity.Group;
import uz.pdp.springbootapprelationships.entity.Student;
import uz.pdp.springbootapprelationships.dto.StudentDto;
import uz.pdp.springbootapprelationships.repository.AddreesRepository;
import uz.pdp.springbootapprelationships.repository.FacultyRepository;
import uz.pdp.springbootapprelationships.repository.GroupRepository;
import uz.pdp.springbootapprelationships.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentrepo;

    @Autowired
    FacultyRepository facultyRepo;

    @Autowired
    GroupRepository groupRepo;

    //VAZIRLIG UCHUN
    //READ
    @GetMapping
    public List<Student> getStudents() {
        List<Student> studentList = studentrepo.findAll();
        return studentList;
    }

    // STUDENT MASUL XODIMI UCHUN
    @GetMapping("/byStudentUnivercityId/{studentUnivercityId}")
    public List<Student> getStudentsByUnivercityId(@PathVariable Integer univercityId) {
        List<Student> allByGroup_faculty_univercity_id = studentrepo.findAllByGroup_Faculty_Univercity_Id(univercityId);
        return allByGroup_faculty_univercity_id;
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(student.getLastName());
        Optional<Group> optionalGroup = groupRepo.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "Such group not found";
        }
        student.setGroup(optionalGroup.get());

        studentrepo.save(student);
        return "Group saved";
    }
}
