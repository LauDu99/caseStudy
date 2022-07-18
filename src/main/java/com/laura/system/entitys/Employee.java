package com.laura.system.entitys;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "Name")
	@NotNull(message="Enter a name")
	@Size(min = 2, max = 50, message = "Name must be between 1 and 50 characters")
	private String firstName;

	@Column(name = "middleName")
	@Size(min = 0, max = 50, message = "Middle name must be between 1 and 50 characters")
	private String middleName;

	@Column(name = "LastName")
	@NotNull(message="Enter a last name")
	@Size(min = 2, max = 50, message = "Last name must be between 1 and 50 characters")
	private String lastName;

	@Column(name = "birthDate")
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	@Past(message="Date is incorrect")
	@NotNull(message="Enter a date")
	private LocalDate birthDate;

	@Column(name = "Position")
	@NotNull(message="Position")
	private String position;

	@OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY)
	private List<Compensation> compensations;
	
	public Employee() {
		
	}
	public Employee(long id, String firstName, String middleName, String lastName, LocalDate birthDate, String position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.position = position;
	}
	
	public Employee( String firstName, String middleName, String lastName, LocalDate birthDate, String position) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	public List<Compensation> getCompensations() {
		return compensations;
	}
	public void setCompensations(List<Compensation> compensations) {
		this.compensations = compensations;
	}
}
	

	