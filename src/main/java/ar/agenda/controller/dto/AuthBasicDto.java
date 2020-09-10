package ar.agenda.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthBasicDto {

	@NotBlank
    @Email(message = "The email must be an email.")
    @Size(max = 40, message = "40 chars max for the email.")
    private String email;

    @Size(min = 4, message = "4 chars min for the password.")
    private String password;
}
