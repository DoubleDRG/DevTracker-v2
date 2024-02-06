package david.DevTracker.repository;

import david.DevTracker.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static david.DevTracker.domain.Activity.*;
import static david.DevTracker.domain.ActivityStack.*;
import static david.DevTracker.domain.ActivityTime.*;
import static david.DevTracker.domain.Member.*;
import static david.DevTracker.domain.Stack.*;
import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ActivityRepositoryTest
{
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    StackRepository stackRepository;

    Member memberA;
    Stack java;

    @BeforeEach
//    @Rollback(value = false)
    void init()
    {
        memberRepository.clear();
        stackRepository.clear();
        activityRepository.clear();

        memberA = createMember("a", "a", "a");
        memberRepository.save(memberA);

        java = createStack("java", "javaUrl");
        stackRepository.save(java);
    }

    @Test
//    @Rollback(value = false)
    void save()
    {
        Activity activity = createActivity("title",
                now(),
                createActivityTime(now(), now().plusHours(2).plusMinutes(24)),
                memberA,
                "content");

        activityRepository.save(activity);
    }
}