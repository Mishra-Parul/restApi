package clipBoard.exercise;

import clipBoard.exercise.Controller.EmployeeController;
import clipBoard.exercise.dto.Employee;
import clipBoard.exercise.service.SummaryStatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTests {

    @Mock
    private SummaryStatisticsService summaryStatisticsService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void getAllRecord_ShouldReturnHttpStatusOk() throws Exception {
        List<Employee> recordList = new ArrayList<>();
        recordList.add(new Employee());
        when(summaryStatisticsService.getAllRecords()).thenReturn(recordList);

        ResponseEntity<List<Employee>> response = employeeController.getAllRecord();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordList, response.getBody());
    }

    @Test
    public void getAllRecord_ShouldReturnHttpStatusBadRequest() throws Exception {
        when(summaryStatisticsService.getAllRecords()).thenThrow(new Exception());

        ResponseEntity<List<Employee>> response = employeeController.getAllRecord();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }


    @Test
    public void addRecord_ShouldReturnHttpStatusCreated() throws Exception {
        List<Employee> recordList = new ArrayList<>();
        recordList.add(new Employee());
        doNothing().when(summaryStatisticsService).addRecordAll(recordList);

        ResponseEntity<String> response = employeeController.addRecord(recordList);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Record added successfully", response.getBody());
    }

    @Test
    public void addRecord_ShouldReturnHttpStatusBadRequest_WithNullPointerException() throws Exception {
        doThrow(new NullPointerException()).when(summaryStatisticsService).addRecordAll(null);

        ResponseEntity<String> response = employeeController.addRecord(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please pass mandatory value  ", response.getBody());
    }

    @Test
    public void addRecord_ShouldReturnHttpStatusBadRequest_WithException() throws Exception {
        doThrow(new Exception("Error while adding record")).when(summaryStatisticsService).addRecordAll(new ArrayList<>());

        ResponseEntity<String> response = employeeController.addRecord(new ArrayList<>());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error adding record: Error while adding record", response.getBody());
    }
}

