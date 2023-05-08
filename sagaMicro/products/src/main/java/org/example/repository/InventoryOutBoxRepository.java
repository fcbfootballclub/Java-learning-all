package org.example.repository;

import org.example.entity.OutBox.InventoryOutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryOutBoxRepository extends  JpaRepository<InventoryOutBox, Integer>{
    List<InventoryOutBox> findAllByPublicStatus(boolean status);
}
