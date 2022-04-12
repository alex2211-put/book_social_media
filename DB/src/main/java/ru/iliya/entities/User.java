package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "nickname"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    private int userID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && openProfile == user.openProfile && nickname.equals(user.nickname) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && birthDate.equals(user.birthDate) && email.equals(user.email) && hashPassword.equals(user.hashPassword) && role.equals(user.role) && Objects.equals(imageLink, user.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, nickname, firstName, lastName, birthDate, email, openProfile, hashPassword, role, imageLink);
    }

    private String nickname;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private boolean openProfile;
    private String hashPassword;
    private Role role;
    private String imageLink;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", nickname='" + nickname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", openProfile=" + openProfile +
                ", hashPassword='" + hashPassword + '\'' +
                ", role=" + role +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    @Column(name = "first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    @Column(name = "last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Column(name = "birth_date")
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    @Column(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Column(name = "open_profile")
    public void setOpenProfile(boolean openProfile) {
        this.openProfile = openProfile;
    }

    public boolean getOpenProfile() {
        return this.openProfile;
    }

    @Column(name = "hash_password")
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getHashPassword() {
        return this.hashPassword;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "image_link")
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return this.imageLink;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userID=" + userID +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", birthdate=" + birthDate + '\'' +
//                ", email='" + email + '\'' +
//                ", openProfile'" + openProfile + '\'' +
//                ", hashPassword'" + hashPassword + '\'' +
//                ", roleID'" + role + '\'' +
//                '}';
//    }

//    @Override
//    public String toString() {
//        return  userID;
//    }

}
