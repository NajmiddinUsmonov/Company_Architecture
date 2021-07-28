package uz.usmonov.lesson2m_1task1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.usmonov.lesson2m_1task1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
