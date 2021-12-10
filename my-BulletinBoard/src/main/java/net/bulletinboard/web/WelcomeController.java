package net.bulletinboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    /**
     * welcome String return 값은 static 디렉토리가 아닌, templates 디렉토리를 찾게 됨
     * 여기선 /helloworld라는 url이 들어오면, templates 디렉토리에 있는 welcome.html 파일을 찾음
     *
     * @GetMapping 어떤 url로 welcome() 메서드를 호출할지 지정(get 방식)
     */
    @GetMapping("/helloworld")
    public String welcome(Model model) {
        model.addAttribute("name", "tsseo");
        model.addAttribute("value", 1000);
        model.addAttribute("taxed_value", 30);
        model.addAttribute("in_ca", true);

        return "welcome";
    }
}
