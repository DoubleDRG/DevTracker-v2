package david.DevTracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Activity
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @Column(length = 100)
    private String title;

    private LocalDateTime createTime;

    @Embedded
    private ActivityTime activityTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = LAZY, mappedBy = "activity", cascade = ALL)
    private List<ActivityStack> activityStacks = new ArrayList<>();

    @Column(length = 20000)
    private String content;

    //생성 메서드
    public static Activity createActivity(String title,
                                   LocalDateTime createTime,
                                   ActivityTime activityTime,
                                   Member member,
                                   String content)
    {
        Activity activity = new Activity();
        activity.title = title;
        activity.createTime = createTime;
        activity.activityTime = activityTime;
        activity.member = member;
        activity.content = content;
        return activity;
    }

    //연관관계 메서드
    public void addActivityStack(ActivityStack activityStack)
    {
        this.activityStacks.add(activityStack);
        activityStack.setActivity(this);
    }

    public void removeActivityStack(String stackName)
    {
        for (ActivityStack activityStack : activityStacks)
        {
            if (activityStack.getStack().getName().equals(stackName))
            {
                System.out.println("activityStack.getStack().getName() = " + activityStack.getStack().getName());
                this.activityStacks.remove(activityStack);
                System.out.println("2. activityStacks = " + activityStacks.size());
                return;
            }
        }
    }

    //비즈니스 메서드
    public void addStack(Stack... stacks)
    {
        for (Stack stack : stacks)
        {
            ActivityStack activityStack = ActivityStack.createActivityStack(stack);
            addActivityStack(activityStack);
        }
    }

    public void removeStack(Stack... stacks)
    {
        for (Stack stack : stacks)
        {
            System.out.println("1. stack = " + stack);
            removeActivityStack(stack.getName());
        }
    }
}
