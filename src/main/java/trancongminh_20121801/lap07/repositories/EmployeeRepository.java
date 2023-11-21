package trancongminh_20121801.lap07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trancongminh_20121801.lap07.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}