package clipBoard.exercise.Controller;

import clipBoard.exercise.dto.Employee;
import clipBoard.exercise.service.SummaryStatisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController()
public class EmployeeController {

    @Autowired
    private SummaryStatisticsService summaryStatisticsService;

    @PostMapping("/employees")
    public ResponseEntity<String> addRecord(@Valid @RequestBody List<Employee> record) throws Exception {
        try {
            summaryStatisticsService.addRecordAll(record);
            return new ResponseEntity<>("Record added successfully", HttpStatus.CREATED);
        } catch (NullPointerException ex){
            return new ResponseEntity<>("Please pass mandatory value  " , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding record: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllRecord() {
        try {
            List<Employee> recordList = summaryStatisticsService.getAllRecords();
            return new ResponseEntity<>(recordList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable("id") UUID employeeId) {
        try {
            summaryStatisticsService.deleteRecord(employeeId);
            return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting record: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
