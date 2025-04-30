package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "choice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer choiceId;

    @Column(nullable = false)
    private Integer questionId;

    @Column(nullable = false)
    private Integer testId;

    @Column(length = 100)
    private String choiceContent;

    @Column(nullable = false)
    private Integer styleId1;

    @Column(nullable = false)
    private Integer styleId2;

    @Column(nullable = false)
    private Integer isDelete;
}
