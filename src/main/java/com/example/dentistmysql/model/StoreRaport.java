package com.example.dentistmysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.dentistmysql.model.Users;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRaport {

    private Long id;

    private String storeType;

    private int Quantity;

    private Users users;

    public String getUsername() {
        if (users == null || id == null) return null;

        return users.getUsername();
    }
}
