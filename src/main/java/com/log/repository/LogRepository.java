package com.log.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.log.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
	List<Log> findByMessageContainingIgnoreCaseOrLevelContainingIgnoreCase(String message, String level);
	// Inside LogRepository
	List<Log> findByDateBetween(LocalDate startDate, LocalDate endDate);
	List<Log> findByLevelIgnoreCase(String level);

    List<Log> findByResourceIdIgnoreCase(String resourceId);
    @Query("SELECT l FROM Log l WHERE l.timestamp >= :startTimestamp AND l.timestamp < :endTimestamp")
    List<Log> findByTimestamp(@Param("startTimestamp") LocalDateTime startTimestamp, @Param("endTimestamp") LocalDateTime endTimestamp);


}