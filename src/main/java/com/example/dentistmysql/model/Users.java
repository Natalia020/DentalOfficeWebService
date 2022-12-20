package com.example.dentistmysql.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")

public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "Username")
    private String username;

    @NotNull
    @Column(name = "Password")
    private String password;

    @NotNull
    @Column(name = "Enabled")
    private Integer enabled;

    @NotNull
    @Column(name = "Role")
    private String role;
}



