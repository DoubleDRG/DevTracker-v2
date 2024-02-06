package david.DevTracker.service;

import david.DevTracker.domain.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static david.DevTracker.domain.Activity.*;
import static david.DevTracker.domain.ActivityStack.*;
import static david.DevTracker.domain.ActivityTime.*;
import static david.DevTracker.domain.Member.*;
import static david.DevTracker.domain.Stack.*;
import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ActivityServiceTest
{
    @Autowired
    EntityManager entityManager;
    @Autowired
    ActivityService activityService;
    @Autowired
    MemberService memberService;
    @Autowired
    StackService stackService;

    Member memberA;
    Stack java;
    Stack python;
    Stack cpp;

    @BeforeEach
//    @Rollback(value = false)
    void init()
    {
        activityService.clear();
        memberService.clear();
        stackService.clear();

        memberA = createMember("a", "a", "a");
        memberService.join(memberA);

        java = createStack("java", "javaUrl");
        stackService.save(java);

        python = createStack("python", "pythonUrl");
        stackService.save(python);

        cpp = createStack("cpp", "cppUrl");
        stackService.save(cpp);

        memberA.addStack(java, python, cpp);
    }

    @Test
    @Rollback(value = false)
    void addStack()
    {
        ActivityTime activityTime = createActivityTime(now(), now().plusHours(1).plusMinutes(24));
        Activity activity = createActivity("title", now(), activityTime, memberA, "content");
        activity.addStack(java, python, cpp);
        activityService.save(activity);
    }

    @Test
    @Rollback(value = false)
    void removeStack()
    {
        ActivityTime activityTime = createActivityTime(now(), now().plusHours(1).plusMinutes(24));
        Activity activity = createActivity("title", now(), activityTime, memberA, "content");
        activity.addStack(java, python, cpp);
        activityService.save(activity);
        activity.removeStack(java, python);
        
    //이상함... 왜 가져와지는거는 1갠데, DB에서 조회하면 3개가 있냐 -> 버그인듯..
    }
}