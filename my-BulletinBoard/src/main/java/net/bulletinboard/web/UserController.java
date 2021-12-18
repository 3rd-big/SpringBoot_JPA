package net.bulletinboard.web;

import net.bulletinboard.domain.User;
import net.bulletinboard.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
/**
 * @RequestMapping
 * url을 매핑해놓으면, 하위 메서드에서 동일하게 url을 사용가능
 * post, get 모두 users로 동일하게 써놨으나, post get 방식에 따라 해당 메서드를 다르게 사용 할 수있음
 */
@RequestMapping("/users")
public class UserController {

//    private List<User> users = new ArrayList<User>();

    /**
     *  @Autowired
     *  해당 애너테이션이 없으면, NullPointException Error
     *  UserRepository는 스프링이 관리함
     *  내가 UserRepository를 사용할테니, 해당 값에 할당 요청
     */
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return "redirect:/users/loginForm";
        }
        if (!user.matchPassword(password)) {
            return "redirect:/users/loginForm";
        }

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        if (HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionedUser = (User) HttpSessionUtils.getUserFromSession(session);
        if (!id.equals(sessionedUser.matchId(id))) {
            throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
        }

        model.addAttribute("user", userRepository.findById(sessionedUser.getId()).get());
        return "/user/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
        if (HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionedUser = (User) HttpSessionUtils.getUserFromSession(session);
        if (!id.equals(sessionedUser.matchId(id))) {
            throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
        }

        User user = userRepository.findById(sessionedUser.getId()).get();
        user.update(updatedUser);
        userRepository.save(user);
        return "redirect:/users";
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
