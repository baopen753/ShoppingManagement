package sample.users;

public class UserDTO {

    private String userName;
    private String passWord;
    private String name;
    private String roleID;

    public UserDTO() {
    }

    public UserDTO(String userName, String passWord, String name, String roleID) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.roleID = roleID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

}
