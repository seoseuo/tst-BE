package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "test")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE test SET is_delete = 0 WHERE test_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "test_name", nullable = false, length = 100)
    private String testName;

    @Column(name = "test_des", length = 255)
    private String testDes;

    @Column(name = "test_detail", length = 455)
    private String testDetail;

    @Column(name = "test_img1", length = 255)
    private String testImg1;

    @Column(name = "test_img2", length = 255)
    private String testImg2;

    @Builder.Default
    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 1;

    @Builder.Default
    @Column(name = "admin_allow", nullable = false)
    private Integer adminAllow = 0 ;
}