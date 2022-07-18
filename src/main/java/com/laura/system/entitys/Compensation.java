package com.laura.system.entitys;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Compensations")
public class Compensation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCompensation;

	@NotNull
	@Column(name="Type")
	private String type;
	
	@NotNull
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="date")
	@DateTimeFormat( pattern = ("dd/MM/yyyy"))
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idEmployee", nullable = false)
	private Employee idEmployee;

	public Compensation() {
	}

	public Compensation(String type, BigDecimal amount, String description, LocalDate date) {
		super();
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public long getIdCompensation() {
		return idCompensation;
	}

	public void setIdCompensation(long idCompensation) {
		this.idCompensation = idCompensation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}
	

}