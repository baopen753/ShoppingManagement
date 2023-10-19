package sample.users;

public class UserError {

    private String usernameError;
    private String nameError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError() {
        this.usernameError = "";
        this.nameError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    public UserError(String usernameError, String nameError, String passwordError, String confirmError, String error) {
        this.usernameError = usernameError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
