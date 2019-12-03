package com.investment.authservice.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

}
