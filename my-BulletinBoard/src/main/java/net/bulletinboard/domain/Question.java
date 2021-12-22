package net.bulletinboard.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id 값을 자동으로 1씩 추가
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

//    private String writer;
    private String title;
    private String contents;

    private LocalDateTime createDate;

    public Question(User writer, String title, String contents) {
        super();
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    /**
     * JPA 에서는 Default 생성자가 필요함
     */
    public Question() {

    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
