/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.entity;

import java.sql.Date;

/**
 *
 * @author alaa
 */
public class User {

    int id;
    String username;
    String email;
    String password;
    String gender;
    String address;
    String name;
    String lastName;
    String bio;
    String phone_number;
    String profilePic;
    Date birthday;

    public User() {
    }

    public User(int id, String username, String email, String gender, String name, String lastName, String phone_number, Date birthday) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.lastName = lastName;
        this.phone_number = phone_number;
        this.birthday = birthday;
    }


    public User(String username, String email, String password, String address, String name, String lastName, String bio, String phone_number) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.name = name;
        this.lastName = lastName;
        this.bio = bio;
        this.phone_number = phone_number;
    }

    public User(String username, String email, String password, String gender, String address, String name, String lastName, String bio, String phone_number, Date birthday) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.name = name;
        this.lastName = lastName;
        this.bio = bio;
        this.phone_number = phone_number;
        this.birthday = birthday;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String name, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public User(String username, String email, String gender, String address, String name, String lastName, String phone_number) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.name = name;
        this.lastName = lastName;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", gender=" + gender + ", address=" + address + ", name=" + name + ", lastName=" + lastName + ", bio=" + bio + ", phone_number=" + phone_number + ", profilePic=" + profilePic + ", birthday=" + birthday + '}';
    }



    public int getId() {
        return id;
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

    public String getProfilePic() {
        return profilePic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
