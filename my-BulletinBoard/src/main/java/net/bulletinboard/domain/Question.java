package net.bulletinboard.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id 값을 자동으로 1씩 추가
    private Long id;

    private String writer;
    private String title;
    private String contents;

    public Question(String writer, String title, String contents) {
        super();
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    /**
     * JPA 에서는 Default 생성자가 필요함
     */
    public Question() {

    }
}
