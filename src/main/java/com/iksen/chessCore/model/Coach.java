package com.iksen.chessCore.model;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "coachs")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_name", nullable = false)
    private String firstName;

    @Column(name = "l_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "otp")
    private Integer otp;

    @Column(name = "otp_expire")
    private LocalDateTime otpExpire;

    @Column(name = "o_auth")
    private String oAuth;

    @Column(name = "oauth_provider")
    private String oauthProvider;

    @Column(name = "rm_id", nullable = false)
    private Long rmId;

    @Column(name = "level_id", nullable = false)
    private Long levelId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "fid_reating")
    private Integer fidReating;

    @Column(name = "op_id")
    private Long opId;


    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Batch> batches;
}

