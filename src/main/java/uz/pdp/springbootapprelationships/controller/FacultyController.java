package uz.pdp.springbootapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapprelationships.entity.Faculty;
import uz.pdp.springbootapprelationships.entity.Univercity;
import uz.pdp.springbootapprelationships.dto.FacultyDto;
import uz.pdp.springbootapprelationships.repository.FacultyRepository;
import uz.pdp.springbootapprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepo;
    @Autowired
    UniversityRepository universityRepo;


    // VAZIRLIG UCHUN
    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepo.findAll();
    }


    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exist = facultyRepo.existsByNameAndUnivercityId(facultyDto.getName(), facultyDto.getUnivercityId());
        if (exist)
            return "This univercity such faculty exist";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<Univercity> optionalUnivercity = universityRepo.findById(facultyDto.getUnivercityId());
        if (!optionalUnivercity.isPresent())
            return "Univercity not found";
        faculty.setUnivercity(optionalUnivercity.get());
        facultyRepo.save(faculty);
        return "Faculty saved";

    }

    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/byUnivrcityId/{univercityId}")
    public List<Faculty> getFacultiesByUnivercityId(@PathVariable Integer univercityId) {
        List<Faculty> allByUnivercityId = facultyRepo.findAllByUnivercityId(univercityId);
        return allByUnivercityId;


    }


    @PutMapping("/?{id}")
    public String edit(@PathVariable Integer id, FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            if (!optionalFaculty.isPresent()) {
                Faculty faculty1 = new Faculty();
                faculty1.setName(facultyDto.getName());
                faculty1.setUnivercity(faculty.getUnivercity());
                return "Success";
            }
            return "Bunday facultitet bor";
        }
        return "Success";
    }


    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        facultyRepo.deleteById(id);
        return "Faculty deleted";
    }

}
