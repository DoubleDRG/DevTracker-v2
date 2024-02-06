package david.DevTracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class ActivityStack
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "activity_stack_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stack_id")
    private Stack stack;

    //생성 메서드
    public static ActivityStack createActivityStack(Stack stack)
    {
        ActivityStack activityStack = new ActivityStack();
        activityStack.stack = stack;
        return activityStack;
    }
}
