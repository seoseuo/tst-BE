package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "choice")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE choice SET is_delete = 0 WHERE choice_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Integer choiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "choice_content", nullable = false, length = 255)
    private String choiceContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @Builder.Default
    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 1;
}
