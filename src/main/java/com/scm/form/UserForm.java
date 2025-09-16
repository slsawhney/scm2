package com.scm.form;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
    private String name;
    private String userName;
    private String password;
}
