package uz.pdp.springbootapprelationships.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapprelationships.entity.Faculty;
import uz.pdp.springbootapprelationships.entity.Group;
import uz.pdp.springbootapprelationships.dto.GroupDto;
import uz.pdp.springbootapprelationships.repository.FacultyRepository;
import uz.pdp.springbootapprelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepo;

    //VAZIRLIG UCHUN
    //READ
    @GetMapping
    public List<Group> getGroups() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    // UNIVERCITETTI MASUL XODIMI UCHUN
    @GetMapping("/byUnivercityId/{univercityId}")
    public List<Group> getGroupsByUnivercityId(@PathVariable Integer univercityId) {
        List<Group> allByFaculty_univercity_id = groupRepository.findAllByFaculty_Univercity_Id(univercityId);
        return allByFaculty_univercity_id;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepo.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group saved";

    }
}
