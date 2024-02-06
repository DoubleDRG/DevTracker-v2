package david.DevTracker.service;


import david.DevTracker.domain.Stack;
import david.DevTracker.repository.MemberRepository;
import david.DevTracker.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class StackService
{
    private final StackRepository stackRepository;
    private final MemberRepository memberRepository;

    public Stack save(Stack stack)
    {
        return stackRepository.save(stack);
    }

    public List<Stack> findAll()
    {
        return stackRepository.findAll();
    }

    public Stack findById(Long stackId)
    {
        return stackRepository.findById(stackId);
    }

    public void clear()
    {
        stackRepository.clear();
    }
}
