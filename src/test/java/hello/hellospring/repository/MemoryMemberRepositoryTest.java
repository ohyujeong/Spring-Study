package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@AfterEach는 메서드 동작이 끝날 때마다 호출
    @AfterEach
    public void afterEach(){
        repository.clearStore();
        //테스트 각각 끝날 때마다 repository 비워줘야 함 (의존성 X 테스트는 독립적이어야 함)
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member); //테스트하기 위해 저장

        //then
        Member result = repository.findById(member.getId()).get(); //optional에서 값 꺼낼때 get()사용
        assertThat(result).isEqualTo(member); //검증단계 result가 meber랑 같냐~
        //assertThat(result).isEqualTo(null); //검증단계 result랑 null이랑 다르니까 오류뜸
    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();
        //result가 member1이 됨

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        //assertThat(result.size()).isEqualTo(3); 멤버 2명뿐이라 이건 에러뜸
    }
}
