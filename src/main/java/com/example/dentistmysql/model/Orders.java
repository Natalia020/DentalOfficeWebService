package com.example.dentistmysql.model;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.dentistmysql.model.Users;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")

public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "OrderType")
    private String orderType;

    @NotNull
    @Column(name = "PatientName")
    private String patientName;

    @NotNull
    @Column(name = "PatientSurname")
    private String patientSurname;

    @NotNull
    @Column(name = "DateFrom")
    private LocalDate dateFrom;

    @NotNull
    @Column(name = "DateTo")
    private LocalDate dateTo;

    @NotNull
    @Column(name = "Info")
    private String info;

    @NotNull
    @Column(name = "Status")
    private String status;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;

    @Transient
    public String getUsername() {
        if (users == null || id == null) return null;

        return users.getUsername();
    }
}
