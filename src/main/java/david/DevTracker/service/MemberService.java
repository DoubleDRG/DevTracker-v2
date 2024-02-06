package david.DevTracker.service;

import david.DevTracker.domain.Member;
import david.DevTracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    public Member join(Member member)
    {
        return memberRepository.save(member);
    }

    public Member findById(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    public void clear()
    {
        memberRepository.clear();
    }
}
