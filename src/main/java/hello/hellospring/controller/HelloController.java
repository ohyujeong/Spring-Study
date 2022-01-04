package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //localhost:8080/hello 에서 아래 함수 실행
    // <Get>Mapping에서 Get은 Get,Post 할 때 Get임. Get 요청이라는 말말
   public String hello(Model model){
        model.addAttribute("data","spring");
        return "hello"; //resources의 templates에서 hello를 찾아서 리턴(해당 파일에 랜더링 해라)
    }
}
