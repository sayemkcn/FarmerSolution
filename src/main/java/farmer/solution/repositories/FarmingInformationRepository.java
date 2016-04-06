package farmer.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farmer.solution.entities.FarmingInformation;

@Repository
public interface FarmingInformationRepository extends JpaRepository<FarmingInformation, Long> {

}
