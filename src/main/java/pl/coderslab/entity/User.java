package pl.coderslab.entity;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    public User(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;

    }
    public  User(){

    };
    public int getId(){return id;}
   public void setId(int id){this.id=id;}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = UserDao.hashPassword(password);
    }

   


    @Override
    public String toString() {
        return "User{" +
                "id='" + id+ '\''+
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
