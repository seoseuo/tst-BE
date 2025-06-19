package com.tst.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_account")
@Where(clause = "is_delete = 1")
@SQLDelete(sql = "UPDATE admin_account SET is_delete = 0 WHERE admin_id = ?")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_email", nullable = false, length = 100, unique = true)
    private String adminEmail;

    @Column(name = "admin_password", nullable = false, length = 100)
    private String adminPassword;

    @Column(name = "admin_name", nullable = false, length = 50)
    private String adminName;

    @Builder.Default
    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 1;
}


