package david.DevTracker.repository;

import david.DevTracker.domain.Activity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ActivityRepository
{
    private final EntityManager entityManager;

    public Activity save(Activity activity)
    {
        entityManager.persist(activity);
        return activity;
    }

    public Activity findById(Long activityId)
    {
        return entityManager.find(Activity.class, activityId);
    }

    public List<Activity> findAll()
    {
        String query = "select a from Activity a";
        return entityManager.createQuery(query, Activity.class).getResultList();
    }

    public void clear()
    {
        entityManager.clear();
    }
}
