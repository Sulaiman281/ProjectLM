package sample;

public class Admin {
    private String username;
    private String password;

    public Admin(String _name, String _password){
        this.username = _name;
        this.password = _password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
