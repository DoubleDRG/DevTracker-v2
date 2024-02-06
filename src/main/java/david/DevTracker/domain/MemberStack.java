package david.DevTracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Literal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class MemberStack
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_stack_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stack_id")
    private Stack stack;

    //생성 메서드
    public static MemberStack createMemberStack(Stack stack)
    {
        MemberStack memberStack = new MemberStack();
        memberStack.stack = stack;
        return memberStack;
    }

}
