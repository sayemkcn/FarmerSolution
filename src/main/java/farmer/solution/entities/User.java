package farmer.solution.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(name="nid_number",nullable=false,unique=true)
	private String nidNumber;
	@Column(name="phone_number",nullable=false)
	private String phoneNumber;
	@Size(min=6,max=12)
	private String password;
	private String category;
	private String token;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Product> productList = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL)
	private List<Question> questionList = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL)
	private List<BankLoanApplication> bankLoanApplicationList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNidNumber() {
		return nidNumber;
	}

	public void setNidNumber(String nidNumber) {
		this.nidNumber = nidNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public List<BankLoanApplication> getBankLoanApplicationList() {
		return bankLoanApplicationList;
	}

	public void setBankLoanApplicationList(List<BankLoanApplication> bankLoanApplicationList) {
		this.bankLoanApplicationList = bankLoanApplicationList;
	}

	

}
