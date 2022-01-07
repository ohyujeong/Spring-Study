package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
        //스프링 빈에 있는 MemberRepository랑 연결
    }

    @Bean
    public MemberRepository memberRepository(){
        //memberRepository 스프링 빈 등록
        return new MemoryMemberRepository();
    }
}
