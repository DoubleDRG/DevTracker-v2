package david.DevTracker.repository;

import david.DevTracker.domain.Member;
import david.DevTracker.domain.MemberStack;
import david.DevTracker.domain.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static david.DevTracker.domain.Member.*;
import static david.DevTracker.domain.MemberStack.*;
import static david.DevTracker.domain.Stack.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest
{
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StackRepository stackRepository;

    @BeforeEach
    void init()
    {
        memberRepository.clear();
        stackRepository.clear();
    }

    @Test
//    @Rollback(value = false)
    void save()
    {
        Member memberA = createMember("a", "a", "a");
        memberA = memberRepository.save(memberA);

        Stack java = createStack("java","javaUrl");
        java = stackRepository.save(java);

        MemberStack memberAJava = createMemberStack(java);
        memberA.addMemberStack(memberAJava);
    }

    @Test
    void findAll()
    {
        Member memberA = createMember("a", "a", "a");
        memberA = memberRepository.save(memberA);

        Member memberB = createMember("a", "a", "a");
        memberB = memberRepository.save(memberB);

        List<Member> list = memberRepository.findAll();
        assertThat(list.size()).isEqualTo(2);
    }
}