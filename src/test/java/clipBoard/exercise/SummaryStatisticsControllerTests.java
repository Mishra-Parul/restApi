package clipBoard.exercise;

import clipBoard.exercise.Controller.SummaryStatisticsController;
import clipBoard.exercise.dto.SummaryStatistics;
import clipBoard.exercise.service.SummaryStatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SummaryStatisticsControllerTests {

    @Mock
    private SummaryStatisticsService summaryStatisticsService;

    @InjectMocks
    private SummaryStatisticsController summaryStatisticsController;

    @Test
    public void testGetSalarySummary() {
        SummaryStatistics expectedSummaryStatistics = new SummaryStatistics();
        when(summaryStatisticsService.fetchOverallStatistics()).thenReturn(expectedSummaryStatistics);
        SummaryStatistics actualSummaryStatistics = summaryStatisticsController.getSalarySummary();
        verify(summaryStatisticsService, times(1)).fetchOverallStatistics();
        assertEquals(expectedSummaryStatistics, actualSummaryStatistics);
    }

    @Test
    public void testGetSalarySummaryOnContract() {
        Map<String, SummaryStatistics> expectedSummaryStatisticsMap = new HashMap<>();
        when(summaryStatisticsService.fetchStatisticsForOnContract()).thenReturn(expectedSummaryStatisticsMap);
        Map<String, SummaryStatistics> actualSummaryStatisticsMap = summaryStatisticsController.getSalarySummaryOnContract();
        verify(summaryStatisticsService, times(1)).fetchStatisticsForOnContract();
        assertEquals(expectedSummaryStatisticsMap, actualSummaryStatisticsMap);
    }

    @Test
    public void testGetSalarySummaryByDepartment() {
        Map<String, SummaryStatistics> expectedSummaryStatisticsList = new HashMap<>();
        when(summaryStatisticsService.fetchStatisticsByDepartment()).thenReturn(expectedSummaryStatisticsList);
        Map<String, SummaryStatistics> actualSummaryStatisticsList = summaryStatisticsController.getSalarySummaryByDepartment();
        verify(summaryStatisticsService, times(1)).fetchStatisticsByDepartment();
        assertEquals(expectedSummaryStatisticsList, actualSummaryStatisticsList);
    }




}

