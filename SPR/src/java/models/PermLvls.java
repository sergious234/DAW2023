/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package models;

/**
 *
 * @author sergio
 */
public enum PermLvls {
    BASIC(0),
    ADMIN(33);

    public int value;

    private PermLvls(int value) {
        this.value = value;
    }

    public int compare(PermLvls other) {
        return Integer.compare(this.value, other.value);
    }

    public boolean is_admin() {
        return this.value == 33;
    }

    public static PermLvls from_int(int val) {
        return switch (val) {
            case 0 ->
                PermLvls.BASIC;
            case 33 ->
                PermLvls.ADMIN;
            default ->
                PermLvls.BASIC;
        };
    }

    public static String view(int value) {
        return switch (value) {
            case 0 ->
                "Basico";
            case 33 ->
                "Admin";
            default ->
                "Basico";
        };
    }
}
