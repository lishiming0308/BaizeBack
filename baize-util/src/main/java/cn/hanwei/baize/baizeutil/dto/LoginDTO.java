package cn.hanwei.baize.baizeutil.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    @NotNull
    @Size(min = 1, max = 20)
    private String userName;

    @NotNull
    @Size(min = 6, max = 32)
    private String password;

    @Size(min = 4, max = 6)
    private String checkCode;
}
