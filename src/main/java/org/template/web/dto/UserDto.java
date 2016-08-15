package org.template.web.dto;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@GroupSequence({
        ValidationLevels.ValidationFirst.class,
        ValidationLevels.ValidationSecond.class,
        ValidationLevels.ValidationThird.class,
        UserDto.class
})
public class UserDto {
    public static final String USER_DTO_ATTR = "userDto";

    private String id;

    @NotBlank(groups = ValidationLevels.ValidationFirst.class)
    @Size(min = 1, max = 50, groups = ValidationLevels.ValidationSecond.class)
    private String email;

    @NotBlank(groups = ValidationLevels.ValidationFirst.class)
    @Size(min = 1, max = 50, groups = ValidationLevels.ValidationSecond.class)
    private String password;

    @NotBlank(groups = ValidationLevels.ValidationFirst.class)
    @Size(min = 1, max = 50, groups = ValidationLevels.ValidationSecond.class)
    private String passwordConfirm;

    private boolean blocked;

    @AssertTrue(groups = ValidationLevels.ValidationFirst.class)
    private boolean agree;

    @AssertTrue(groups = ValidationLevels.ValidationFirst.class)
    public boolean isPasswordsMatch() {
        return this.password != null && this.password.equals(this.passwordConfirm);
    }

    @AssertTrue(groups = ValidationLevels.ValidationFirst.class)
    public boolean isCorrectEmail() {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}
