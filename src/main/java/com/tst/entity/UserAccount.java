package com.tst.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_account")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE user_account SET is_delete = 0 WHERE user_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT 사용
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_email", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_join_date", nullable = false)
    private LocalDateTime userJoinDate;

    @Column(name = "is_delete", nullable = false)
    private Integer isDelete;

    @Column(name = "login_type", nullable = false, length = 20)
    private String loginType;
}
