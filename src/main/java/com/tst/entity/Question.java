package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "question")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE question SET is_delete = 0 WHERE question_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "question_content", nullable = false, length = 255)
    private String questionContent;

    @Column(name = "is_delete", nullable = false)
    private Integer isDelete;
}
