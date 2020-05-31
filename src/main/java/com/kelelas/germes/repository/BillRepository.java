package com.kelelas.germes.repository;

import com.kelelas.germes.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> getAllByStatusIdAndUserId(Long status, Long userId);
    List<Bill> getAllByStatusId(Long status);
    List<Bill> getAllByUserId(Long userId);
}
