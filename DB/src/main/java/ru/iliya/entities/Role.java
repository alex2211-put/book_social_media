package ru.iliya.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    private int roleID;
    private String name;
    private int mask;

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

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", name='" + name + '\'' +
                ", mask='" + mask + '\'' +
                '}';
    }

    private Role role;

    @ManyToOne
    @JoinColumn(name = "roleID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
