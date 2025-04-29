package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "style")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer styleId;

    @Column(nullable = false)
    private Integer testId;

    @Column(length = 200)
    private String styleImg;

    @Column(nullable = false)
    private Integer isDelete;
}
