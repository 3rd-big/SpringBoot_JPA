package net.bulletinboard.web;

import net.bulletinboard.domain.Question;
import net.bulletinboard.domain.QuestionRepository;
import net.bulletinboard.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    /**
     * @Autowired QuestionRepository 는 interface, 스프링이 구현체를 만들어 관리함
     * 우리는 해당 애너테이션을 사용해서 해당 구현체를 가져다씀
     */
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/users/loginForm";
        }
        return "/qna/form";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/users/loginForm";
        }

        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        /**
         * Object 에서 get() method를 사용하는건 안좋은 습관!!
         */
        Question newQuestion = new Question(sessionUser, title, contents);
        questionRepository.save(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, String title, String contents) {
        Question question = questionRepository.findById(id).get();
        question.update(title, contents);
        questionRepository.save(question);
        return String.format("redirect:/questions/%d", id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Question question = questionRepository.findById(id).get();
        questionRepository.delete(question);
        return "redirect:/";
    }

}
