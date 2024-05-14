package com.log.controller;

import com.log.model.Log;
import com.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/ingest")
    public ResponseEntity<Map<String, Object>> ingestLog(@RequestBody Log log) {
        System.out.println("Received log data: " + log.toString());
        
        // Save the log to the database using the LogService
        logService.ingestLog(log);

        // Return a JSON response with the log ID
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Log ingested successfully!");
        response.put("id", log.getId());
        
        return ResponseEntity.ok(response);
    }


    // New endpoint for searching logs
    @GetMapping("/search")
    public ResponseEntity<List<Log>> searchLogs(
            @RequestParam(name = "searchTerm", required = false) String searchTerm,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "level", required = false) String level,
            @RequestParam(name = "resourceId", required = false) String resourceId,
            @RequestParam(name = "timestamp", required = false) String timestamp) {

        // Log debug information
        System.out.println("Received search parameters - searchTerm: " + searchTerm + ", startDate: " + startDate + ", endDate: " + endDate + ", level: " + level + ", resourceId: " + resourceId + ", timestamp: " + timestamp);

        // Search logs using the LogService
        List<Log> searchResults = logService.searchLogs(searchTerm, startDate, endDate, level, resourceId, timestamp);

        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }


}
