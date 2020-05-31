package com.kelelas.germes.repository;


import com.kelelas.germes.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByUserId(Long userId);
    List<History> findAllByStatusId(Long statusId);
    List<History> findAllByStatusIdAndUserId(Long statusId, Long userId);

}
