package tunisiagottalent.Entity;
public class User {
    int id;

    public int getId() {
        return id;
    }
    String username;
    String email;
    String password;
    String gender;
    String address;
    String name;
    String lastName;
    String bio;
    String phone_number;
    String Role;

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
       
    }

    public User(String username, String email, String gender, String address, String name, String lastName, String phone_number,String Role) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.name = name;
        this.lastName = lastName;
        this.phone_number = phone_number;
        this.Role=Role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", gender=" + gender + ", address=" + address + ", name=" + name + ", lastName=" + lastName + ", bio=" + bio + ", phone_number=" + phone_number + ", Role=" + Role + '}';
    }

    

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


}

