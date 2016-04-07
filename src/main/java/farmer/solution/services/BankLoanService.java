package farmer.solution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farmer.solution.entities.BankLoanApplication;
import farmer.solution.repositories.BankLoanApplicationRepository;

@Service
public class BankLoanService {
	@Autowired
	private BankLoanApplicationRepository bankLoanApplicationRepository;
	
	public BankLoanApplication saveApplication(BankLoanApplication bankLoanApplication){
		return bankLoanApplicationRepository.saveAndFlush(bankLoanApplication);
	}
}
