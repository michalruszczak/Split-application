package pl.ruszczak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ruszczak.model.Payment;


@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
    public Optional<Payment> findById(Long id);
}
