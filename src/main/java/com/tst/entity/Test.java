package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @Column(nullable = false, length = 100)
    private String testName;

    @Column(length = 100)
    private String testDes;

    @Column(length = 455)
    private String testDetail;

    @Column(length = 200)
    private String testImg1;

    @Column(length = 200)
    private String testImg2;

    @Column(nullable = false)
    private Integer isDelete;
}
