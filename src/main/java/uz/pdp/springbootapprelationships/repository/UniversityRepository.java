package uz.pdp.springbootapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootapprelationships.entity.Univercity;

@Repository
public interface UniversityRepository extends JpaRepository<Univercity,Integer> {
}
