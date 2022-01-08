package com.investment.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;
}
