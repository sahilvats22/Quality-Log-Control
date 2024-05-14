package com.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.log.model.Log;
import com.log.repository.LogRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> searchLogs(String searchTerm, String startDateStr, String endDateStr, String level, String resourceId, String timestampStr) {
        // Convert timestampStr to LocalDateTime object
        LocalDateTime timestampDateTime = parseTimestamp(timestampStr);

        // Convert startDateStr and endDateStr to LocalDate objects
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);

        // Create separate lists for each filter
        List<Log> searchResults = logRepository.findByMessageContainingIgnoreCaseOrLevelContainingIgnoreCase(searchTerm, searchTerm);
        List<Log> dateRangeResults = new ArrayList<>();
        List<Log> levelResults = new ArrayList<>();
        List<Log> resourceIdResults = new ArrayList<>();
        List<Log> timestampResults = new ArrayList<>();

        // Apply filters based on the bonus features
        if (startDate != null && endDate != null) {
            dateRangeResults = logRepository.findByDateBetween(startDate, endDate);
        }

        if (level != null) {
            levelResults = logRepository.findByLevelIgnoreCase(level);
        }

        if (resourceId != null) {
            resourceIdResults = logRepository.findByResourceIdIgnoreCase(resourceId);
        }

        if (timestampDateTime != null) {
            LocalDateTime endTimestamp = timestampDateTime.plusDays(1); // Adjusted to the end of the day
            timestampResults = logRepository.findByTimestamp(timestampDateTime, endTimestamp);
        }

        // Combine searchResults and other results
        searchResults.addAll(dateRangeResults);
        searchResults.addAll(levelResults);
        searchResults.addAll(resourceIdResults);
        searchResults.addAll(timestampResults);

        System.out.println("Received timestampStr: " + timestampStr);

        return searchResults;
    }


    public void ingestLog(Log log) {
        // Save the log to the database
        logRepository.save(log);
    }
    
    private LocalDateTime parseTimestamp(String timestampStr) {
        try {
            // Assuming the timestamp format is yyyy-MM-dd'T'HH:mm:ss'Z'
            return LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
            // Handle parsing exception
            return null;
        }
    }

    private LocalDate parseDate(String dateStr) {
        try {
            // Assuming the date format is yyyy-MM-dd'T'HH:mm:ss'Z'
            return LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            // Handle parsing exception
            return null;
        }
    }
}
