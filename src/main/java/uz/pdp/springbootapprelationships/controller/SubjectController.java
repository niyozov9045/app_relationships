package uz.pdp.springbootapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapprelationships.entity.Subject;
import uz.pdp.springbootapprelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepo;

    //CRUD

    //READ
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepo.findAll();
        return subjectList;
    }

    //CREAD
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepo.existsByName(subject.getName());
        if (existsByName) {
            return "This subject alredy exist";
        }
        subjectRepo.save(subject);
        return "Subject added";
    }

    //UPDATE
//    @PutMapping("/{id}")
//    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
//        Optional<Subject> optionalSubject = subjectRepo.findById(id);
//        if(!optionalSubject.isPresent()) {
//            Subject subject1 = optionalSubject.get();
//            subject1.setName(subject.getName());
//        }
//        return "Subject edited";
//    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepo.deleteById(id);
        return "Subject deleted";
    }
}
