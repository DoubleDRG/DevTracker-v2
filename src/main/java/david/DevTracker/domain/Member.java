package david.DevTracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static david.DevTracker.domain.MemberStack.*;
import static david.DevTracker.domain.MemberType.*;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String nickname;

    @Enumerated(STRING)
    private MemberType type;

    @OneToMany(fetch = LAZY, mappedBy = "member", cascade = ALL)
    private List<MemberStack> memberStacks = new ArrayList<>();


    //생성 메서드
    public static Member createMember(String username, String password, String nickname)
    {
        Member member = new Member();
        member.username = username;
        member.password = password;
        member.nickname = nickname;
        member.type = NORMAL;
        return member;
    }

    //연관관계 메서드
    public void addMemberStack(MemberStack memberStack)
    {
        this.memberStacks.add(memberStack);
        memberStack.setMember(this);
    }

    public void removeMemberStack(String stackName)
    {
        for (MemberStack memberStack : memberStacks)
        {
            if (memberStack.getStack().getName().equals(stackName))
            {
                this.memberStacks.remove(memberStack);
                return;
            }
        }
    }

    //비즈니스 메서드
    public void addStack(Stack... stacks)
    {
        for (Stack stack : stacks)
        {
            MemberStack memberStack = createMemberStack(stack);
            addMemberStack(memberStack);
        }
    }

    public void removeStack(Stack... stacks)
    {
        for (Stack stack : stacks)
        {
            removeMemberStack(stack.getName());
        }
    }

}
