package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(nullable = false)
    private Integer testId;

    @Column(length = 100)
    private String questionContent;

    @Column(nullable = false)
    private Integer isDelete;
}
