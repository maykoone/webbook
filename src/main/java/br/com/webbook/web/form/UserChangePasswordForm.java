/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.web.form;

import br.com.webbook.validation.constraint.FieldMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author maykoone
 */
@FieldMatch(first = "newPassword", second = "passwordVerification", message = "A nova senha não corresponde com a senha de verificação")
public class UserChangePasswordForm {

    @NotNull(message = "A senha atual é obrigatória")
    @NotBlank(message = "A senha atual é obrigatória")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.")
    private String oldPassword;
    @NotNull(message = "A nova senha é obrigatória")
    @NotBlank(message = "A nova senha é obrigatória")
    @Size(min = 3, message = "A nova senha deve ter no mínimo 3 caracteres.")
    private String newPassword;
    @NotNull(message = "A senha de verificação é obrigatória")
    @NotBlank(message = "A senha de verificação é obrigatória")
    private String passwordVerification;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }
}
