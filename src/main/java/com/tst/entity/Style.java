package com.tst.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "style")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE style SET is_delete = 0 WHERE style_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_id")
    private Integer styleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "style_name", nullable = false, length = 255)
    private String styleName;

    @Column(name = "style_content", nullable = false, length = 455)
    private String styleContent;

    @Column(name = "style_img", length = 255)
    private String styleImg;

    @Builder.Default
    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 1;
}
