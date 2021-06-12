package pl.ruszczak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ruszczak.model.Bill;

@Repository
public interface BillRepository extends JpaRepository <Bill, Long> {
    Bill findByDescription(String description);
}
