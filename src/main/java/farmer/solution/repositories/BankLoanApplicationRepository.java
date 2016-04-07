package farmer.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farmer.solution.entities.BankLoanApplication;

@Repository
public interface BankLoanApplicationRepository extends JpaRepository<BankLoanApplication, Long>{

}
