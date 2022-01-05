package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //011같은 key값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id는 그냥 시스템이 정해주게 함(sequence로 생성)
        store.put(member.getId(), member); //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //store에서 꺼내줌.. 근데 null일 가능성이 있으면 ohNullable로 감싸서 꺼내줌
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store에 있는 member들 List로 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                //파라미터로 넘어온 name이랑 store name이랑 같은 지 비교
                .findAny();
                //그 중에서 찾으면 반환
    }

    public void clearStore(){
        store.clear();
    }
}
