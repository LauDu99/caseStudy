package com.laura.system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laura.system.entitys.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long>{

	@Query("SELECT e FROM Employee e WHERE" + " CONCAT(e.firstName, e.lastName, e.birthDate, e.position)"
			+ " LIKE %?1%")
	public List<Compensation> findAll(String keyWord);
	
	@Query("SELECT c FROM Compensation c WHERE id_Employee = :idEmployee")
	public List<Compensation> getCompensationsEmployeeById(@Param("idEmployee") Long idEmployee);
	
	@Query(value="SELECT id_compensation, type, SUM(amount) as amount, description, date, MONTHNAME(date) as month, YEAR(date) as year, id_employee FROM Compensations WHERE id_Employee = :idEmployee AND date BETWEEN :startDate AND :endDate GROUP BY month, year", nativeQuery=true)
	public List<Compensation> getCompensationsEmployeeByDate(@Param("idEmployee") Long idEmployee, @Param("startDate") LocalDate startDate,  @Param("endDate") LocalDate endDate);
	
	@Query(value="SELECT * FROM Compensations WHERE id_Employee = :idEmployee AND MONTH(date) = :month AND YEAR(date) = :year", nativeQuery=true)
	public List<Compensation> getCompensationsEmployeeByMonth(@Param("idEmployee") Long idEmployee, @Param("month") int month,  @Param("year") int year);
	
	//@Query("SELECT c FROM Compensation c WHERE id_Employee = :idEmployee AND MONTH(c.date) = :month AND YEAR(c.date) = :year")
	//public List<Compensation> getCompensationsEmployeeByMonth(@Param("idEmployee") Long idEmployee, @Param("month") int month,  @Param("year") int year);
	
	
}
