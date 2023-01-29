package clipBoard.exercise.service;

import clipBoard.exercise.dto.Employee;
import clipBoard.exercise.dto.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SummaryStatisticsServiceImpl implements SummaryStatisticsService {

    private Map<UUID, Employee> employeeList = new HashMap<>();
    private Map<String, SummaryStatistics> departmentSummaryStatistics = new HashMap<>();
    private Map<String, Map<String, SummaryStatistics>> departmentSubdepartmentSummary = new HashMap<>();


    private Boolean globalOnContract = false;

    @Override
    public void addRecordAll(List<Employee> record) {
        record.stream().forEach(this::addRecord);
    }

    public void addRecord(Employee record) {
        UUID employeeId = UUID.randomUUID();
        record.setEmployeeId(employeeId);
        if(record.getOnContract()){
            globalOnContract = true;
        }
        employeeList.put(employeeId, record);
    }

    public void deleteRecord(UUID employeeId) throws Exception {
        Employee employee = employeeList.get(employeeId);
        if(Objects.isNull(employee)) {
            throw new Exception("Employee does not exist");
        }
        employeeList.remove(employeeId);
    }

    public SummaryStatistics fetchOverallStatistics() {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Employee employee : employeeList.values()) {
            stats.addValue(Double.parseDouble(employee.getSalary()));
        }
        return SummaryStatistics.builder().mean(stats.getMean()).max(stats.getMax()).min(stats.getMin()).build();
    }

    public Map<String,SummaryStatistics> fetchStatisticsForOnContract() {
        Map<String, SummaryStatistics> response = new HashMap<>();
        if (globalOnContract) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (Employee employee : employeeList.values()) {
                if (employee.getOnContract() != null && employee.getOnContract().equals(Boolean.TRUE)) {
                    stats.addValue(Double.parseDouble(employee.getSalary()));
                }
            }
            response.put("Data :",SummaryStatistics.builder().mean(stats.getMean()).max(stats.getMax()).min(stats.getMin()).build());
        } else {
            response.put("No OnContract Employee Found",SummaryStatistics.builder().build());
        }
        return response;
    }

    public Map<String, SummaryStatistics> fetchStatisticsByDepartment() {
        Map<String, List<Employee>> employeesByDepartment = employeeList.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // Calculate summary statistics for each department
        Map<String, SummaryStatistics> statisticsByDepartment = new HashMap<>();
        for (Map.Entry<String, List<Employee>> entry : employeesByDepartment.entrySet()) {
            List<Employee> departmentEmployees = entry.getValue();
            DescriptiveStatistics stats = new DescriptiveStatistics();
            departmentEmployees.forEach(e -> stats.addValue(Double.parseDouble(e.getSalary())));
            SummaryStatistics statistics = new SummaryStatistics();
            statistics.setMean(stats.getMean());
            statistics.setMax(stats.getMax());
            statistics.setMin(stats.getMin());

            statisticsByDepartment.put(entry.getKey(), statistics);
        }

        return statisticsByDepartment;
    }

    public Map<String, Map<String, SummaryStatistics>> getSalarySummaryByDepartmentAndSubDepartment() {
        Map<String, Map<String, List<Employee>>> departmentSubDepartmentMap = employeeList.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.groupingBy(Employee::getSubDepartment)));
        departmentSubDepartmentMap.forEach((department, subDepartmentMap) -> {
            Map<String, SummaryStatistics> subDepartmentSummary = new HashMap<>();
            subDepartmentMap.forEach((subDepartment, employees) -> {
                DoubleSummaryStatistics stats =  employees.stream().mapToDouble(employee -> Double.parseDouble(employee.getSalary())).summaryStatistics();
                subDepartmentSummary.put(subDepartment, SummaryStatistics.builder().max(stats.getMax()).min(stats.getMin()).mean(stats.getAverage()).build());
            });
            departmentSubdepartmentSummary.put(department, subDepartmentSummary);
        });
        return departmentSubdepartmentSummary;
    }

    @Override
    public List<Employee> getAllRecords() {
        return this.employeeList.values().stream().collect(Collectors.toList());
    }

}
