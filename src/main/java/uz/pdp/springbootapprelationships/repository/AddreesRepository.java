package uz.pdp.springbootapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springbootapprelationships.entity.Address;

@Repository
public interface AddreesRepository extends JpaRepository<Address,Integer> {
}
