package com.example.dentistmysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.dentistmysql.model.Users;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Raport {

    private Long id;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String patientName;

    private String patientSurname;

    private String orderType;

    private String info;

    private String status;

    private Users users;

    public String getUsername() {
        if (users == null || id == null) return null;

        return users.getUsername();
    }
}
