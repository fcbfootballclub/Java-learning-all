package org.payment.repository;

import org.core.entity.PaymentEventOutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentOutBoxRepository extends JpaRepository<PaymentEventOutBox, Integer> {
    List<PaymentEventOutBox> findAllByPublicStatus(boolean status);
}