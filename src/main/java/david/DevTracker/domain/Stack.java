package david.DevTracker.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Stack
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stack_id")
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String imageUrl;

    //생성 메서드
    public static Stack createStack(String name, String imageUrl)
    {
        Stack stack = new Stack();
        stack.name = name;
        stack.imageUrl = imageUrl;
        return stack;
    }
}
