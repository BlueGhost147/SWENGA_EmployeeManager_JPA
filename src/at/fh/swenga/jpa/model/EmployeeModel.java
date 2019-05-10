package at.fh.swenga.jpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "Employee")
public class EmployeeModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String firstName;

	@Column(nullable = false, length = 30)
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dayOfBirth;

	@Version
	long version;

	@Embedded
	private Address billingAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
			@AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
			@AttributeOverride(name = "country", column = @Column(name = "delivery_country")),
			@AttributeOverride(name = "zip", column = @Column(name = "delivery_zip")) })
	private Address deliveryAddress;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Department department;

	/*
	 * @JoinTable( name="EMP_PROJ", joinColumns={
	 * 
	 * @JoinColumn(name="EMP_ID", referencedColumnName="ID")},
	 * inverseJoinColumns={@JoinColumn(name="PROJ_ID", referencedColumnName="ID")})
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Project> projects;

	public EmployeeModel() {
	}

	public EmployeeModel(String firstName, String lastName, Date dayOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dayOfBirth = dayOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public void addProject(Project project) {
		if (projects== null) {
			projects= new ArrayList<Project>();
		}
		this.projects.add(project);
	}


}