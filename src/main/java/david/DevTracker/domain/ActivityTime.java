package david.DevTracker.domain;

import jakarta.persistence.Embeddable;

import java.time.Duration;
import java.time.LocalDateTime;

@Embeddable
public class ActivityTime
{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;

    //생성 메서드
    public static ActivityTime createActivityTime(LocalDateTime startTime,
                                                  LocalDateTime endTime)
    {
        ActivityTime activityTime = new ActivityTime();
        activityTime.startTime = startTime;
        activityTime.endTime = endTime;
        activityTime.duration = Duration.between(startTime, endTime);
        return activityTime;
    }
}
