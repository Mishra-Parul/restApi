package clipBoard.exercise.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SummaryStatistics {
    private double mean;
    private double min;
    private double max;
}
