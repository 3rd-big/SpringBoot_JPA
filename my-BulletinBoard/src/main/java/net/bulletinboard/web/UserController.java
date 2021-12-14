package net.bulletinboard.web;

import net.bulletinboard.domain.User;
import net.bulletinboard.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * @RequestMapping
 * url을 매핑해놓으면, 하위 메서드에서 동일하게 url을 사용가능
 * post, get 모두 users로 동일하게 써놨으나, post get 방식에 따라 해당 메서드를 다르게 사용 할 수있음
 */
@RequestMapping("/users")
public class UserController {

//    private List<User> users = new ArrayList<User>();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
//        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("user", userRepository.findById(id).get());
        return "/user/updateForm";
    }

    @PostMapping("")
    public String create(User user) {
        System.out.println("user = " + user);
        userRepository.save(user);

        /**
         *  list만 리턴하면 templates 디렉토리의 list.html만 호출
         *  return "list";
         */
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }
}
