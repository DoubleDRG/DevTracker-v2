package david.DevTracker.repository;

import david.DevTracker.domain.Stack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class StackRepository
{
    private final EntityManager entityManager;

    public Stack save(Stack stack)
    {
        entityManager.persist(stack);
        return stack;
    }

    public List<Stack> findAll()
    {
        String query = "select s from Stack s";
        return entityManager.createQuery(query, Stack.class).getResultList();
    }

    public Stack findById(Long stackId)
    {
        return entityManager.find(Stack.class, stackId);
    }

    public void clear()
    {
        entityManager.clear();
    }
}
