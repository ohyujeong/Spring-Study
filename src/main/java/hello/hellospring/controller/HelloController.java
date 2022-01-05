package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //localhost:8080/hello 에서 아래 함수 실행
    // <Get>Mapping에서 Get은 Get,Post 할 때 Get임. Get 요청이라는 말
   public String hello(Model model){
        //Model을 통해 template에서 쓸 수 있도록 넘겨줌
        model.addAttribute("data","spring");
        //attributevalue : 스프링에서 직접 파라미터를 받음
        return "hello"; //resources의 templates에서 hello를 찾아서 리턴(해당 파일에 랜더링 해라)
    }

    //mvc 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //외부에서 파라미터를 받음
        //RequestParam에 마우스 대고 ctrl+p 누르면 파라미터 옵션(정보) 볼 수 있음
        //localhost:8080/hello-mvc?name=쓰고싶은 내용
        model.addAttribute("name", name);
        //파라미터에서 받아온 name을 넘겨줌. "name"이 key고 String name == name
        return "hello-template";
    }

    //Api 방식
    @GetMapping("hello-string")
    @ResponseBody // 이걸 사용하면 viewResolver를 사용하지 않고 HTTP의 Body에 문자 내용을 직접 반환
   public String helloString(@RequestParam("name")String name){
        return "hello " + name;
    }

    //@ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON(Key : Value)으로 변환됨
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // Hello 객체 생성, ctrl+shift+enter치면 따옴표 안 쳐도 알아서 완성해줌
        hello.setName(name); //value가 됨 setName메서드에 name전달
        return hello; //객체반환
    }

    static class Hello {
        private String name; //key가 됨, private이라서 getName같은 메서드를 통해 접근

        //getter and setter 단축키 alt+insert
        //꺼낼 때 getName 넣을 때 setName
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
