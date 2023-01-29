package clipBoard.exercise.Controller;

import clipBoard.exercise.dto.SummaryStatistics;
import clipBoard.exercise.service.SummaryStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class SummaryStatisticsController {

    @Autowired
    private SummaryStatisticsService summaryStatisticsService;

    @GetMapping("/summary")
    public SummaryStatistics getSalarySummary() {
        return summaryStatisticsService.fetchOverallStatistics();
    }

    @GetMapping("/summary/onContract")
    public Map<String, SummaryStatistics> getSalarySummaryOnContract() {
        return summaryStatisticsService.fetchStatisticsForOnContract();
    }

    @GetMapping("/summary/department")
    public Map<String, SummaryStatistics> getSalarySummaryByDepartment() {
        return summaryStatisticsService.fetchStatisticsByDepartment();
    }

    @GetMapping("/summary/department/subdepartment")
    public Map<String, Map<String, SummaryStatistics>> getSalarySummaryByDepartmentAndSubDepartment() {
        return summaryStatisticsService.getSalarySummaryByDepartmentAndSubDepartment();
    }
}
