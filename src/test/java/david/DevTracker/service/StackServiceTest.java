package david.DevTracker.service;

import david.DevTracker.domain.Member;
import david.DevTracker.domain.MemberStack;
import david.DevTracker.domain.Stack;
import david.DevTracker.repository.ActivityRepository;
import david.DevTracker.repository.MemberRepository;
import david.DevTracker.repository.StackRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static david.DevTracker.domain.Member.createMember;
import static david.DevTracker.domain.MemberStack.*;
import static david.DevTracker.domain.Stack.createStack;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StackServiceTest
{
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
    }

    @Test
//    @Rollback(value = false)
    void addStack()
    {
        memberA.addStack(java, python, cpp);
        assertThat(stackService.findAll().size()).isEqualTo(3);
    }

    @Test
//    @Rollback(value = false)
    void removeStack()
    {
        memberA.addStack(java, python, cpp);
        memberA.removeStack(java, python);
    }
}