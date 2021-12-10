package net.bulletinboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<User>();

    @PostMapping("/create")
//     이런 파라미터형식은 비효율적이므로 User 클래스를 활용
//    public String create(String userId, String password, String name, String email) {
    public String create(User user) {
        System.out.println("user = " + user);
        users.add(user);
        /**
         *  list만 리턴하면 templates 디렉토리의 list.html만 호출
         *  return "list";
         */
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", users);
        return "list";
    }
}
