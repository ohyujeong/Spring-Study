package hello.hellospring.controller;

public class MemberForm {
    private String name; //얘랑 createMemberForm.html name이랑 매칭됨

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
