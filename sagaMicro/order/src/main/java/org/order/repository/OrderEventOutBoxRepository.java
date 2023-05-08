package org.order.repository;

import org.core.entity.OrderEventOutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEventOutBoxRepository extends JpaRepository<OrderEventOutBox, Integer> {
    List<OrderEventOutBox> findAllByPublicStatus(boolean publicStatus);

    @Query(value = "select * from order_event_out_box u where u.public_status = ?1", nativeQuery = true)
    List<OrderEventOutBox> findList(Integer status);
}
