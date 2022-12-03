package uz.pdp.springbootapprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapprelationships.entity.Address;
import uz.pdp.springbootapprelationships.entity.Univercity;
import uz.pdp.springbootapprelationships.dto.UniversityDto;
import uz.pdp.springbootapprelationships.repository.AddreesRepository;
import uz.pdp.springbootapprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/univercity")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepo;
    @Autowired
    AddreesRepository addreesRepo;

    //CRUD AMALLARI:


    //READ
    @GetMapping
    public List<Univercity> getUniversity() {
        List<Univercity> univercityList = universityRepo.findAll();
        return univercityList;
    }

    //CREAD
    @PostMapping
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address addrees = new Address();
        addrees.setCity(universityDto.getCity());
        addrees.setDistrict(universityDto.getDistrict());
        addrees.setStreet(universityDto.getStreet());
        Address saveAddress = addreesRepo.save(addrees);

        Univercity univercity = new Univercity();
        univercity.setName(universityDto.getName());
        univercity.setAddress(saveAddress);
        universityRepo.save(univercity);
        return "Univercity added";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<Univercity> optionalUniversity = universityRepo.findById(id);
        if (optionalUniversity.isPresent()) {
            Univercity univercity = optionalUniversity.get();
            univercity.setName(universityDto.getName());
            //Universitet addressi
            Address address = univercity.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addreesRepo.save(address);
            universityRepo.save(univercity);
            return "University edited";
        }
        return "University not found";
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepo.deleteById(id);
        return "University deleted";
    }
}
