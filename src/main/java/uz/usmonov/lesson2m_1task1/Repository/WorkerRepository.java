package uz.usmonov.lesson2m_1task1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.usmonov.lesson2m_1task1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumberAndIdNot(String phoneNumber,Integer id);
}
