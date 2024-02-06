package david.DevTracker.service;

import david.DevTracker.domain.Activity;
import david.DevTracker.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class ActivityService
{
    private final ActivityRepository activityRepository;

    public Activity save(Activity activity)
    {
        return activityRepository.save(activity);
    }

    public Activity findById(Long activityId)
    {
        return activityRepository.findById(activityId);
    }

    public List<Activity> findAll()
    {
        return activityRepository.findAll();
    }

    public void clear()
    {
        activityRepository.clear();
    }
}
