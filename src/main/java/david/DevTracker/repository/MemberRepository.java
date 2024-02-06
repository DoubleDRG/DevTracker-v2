package david.DevTracker.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import david.DevTracker.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class MemberRepository
{
    private final EntityManager entityManager;

    public Member save(Member member)
    {
        entityManager.persist(member);
        return member;
    }

    public Member findById(Long memberId)
    {
        return entityManager.find(Member.class, memberId);
    }

    public List<Member> findAll()
    {
        String query = "select m from Member m";
        return entityManager.createQuery(query, Member.class).getResultList();
    }

    public void clear()
    {
        entityManager.clear();
    }
}
