package clipBoard.exercise.service;

import clipBoard.exercise.dto.Employee;
import clipBoard.exercise.dto.SummaryStatistics;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SummaryStatisticsService {

    public void addRecord(Employee employee);

    public void deleteRecord(UUID employeeId) throws Exception;

    public SummaryStatistics fetchOverallStatistics();

    public Map<String, SummaryStatistics> fetchStatisticsForOnContract();

    public Map<String, SummaryStatistics> fetchStatisticsByDepartment();
    public Map<String, Map<String, SummaryStatistics>> getSalarySummaryByDepartmentAndSubDepartment();

    List<Employee> getAllRecords() throws Exception;

    void addRecordAll(List<Employee> record) throws Exception;
}