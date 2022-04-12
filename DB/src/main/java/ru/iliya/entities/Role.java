package ru.iliya.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    private int roleID;
    private String name;
    private int mask;
    enum Names {
        admin,
        developer,
        user
    }

    public Role(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleID == role.roleID && mask == role.mask && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, name, mask);
    }

    public Role(int roleID) {
        this.roleID = roleID;
        this.mask = roleID;
        this.name = Names.values()[roleID].toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public int getRoleID() {
        return this.roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Column(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Column(name = "mask")
    public int getMask() {
        return this.mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public Set<SimpleGrantedAuthority> calcAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("1"));
        return authorities;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", name='" + name + '\'' +
                ", mask='" + mask + '\'' +
                '}';
    }

}
