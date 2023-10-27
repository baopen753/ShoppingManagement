package sample.users;

public class UserDTO {

    private String userID;
    private String userName;
    private String passWord;
    private String name;
    private String roleID;

    public UserDTO() {
        this.userID = "";
        this.userName = "";
        this.passWord = "";
        this.name = "";
        this.roleID = "";
    }

    public UserDTO(String userID, String userName, String passWord, String name, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.roleID = roleID;
    }

  

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
