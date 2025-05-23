package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Commentable;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class UserImpl implements User{

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
    private static final String LASTNAME_LEN_ERR = format(
            "Lastname must be between %s and %s characters long!",
            LASTNAME_LEN_MIN,
            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
    private static final String FIRSTNAME_LEN_ERR = format(
            "Firstname must be between %s and %s characters long!",
            FIRSTNAME_LEN_MIN,
            FIRSTNAME_LEN_MAX);

    private final static String NOT_AN_VIP_USER_VEHICLES_ADD = "You are not VIP and cannot add more than %d vehicles!";
    private final static String ADMIN_CANNOT_ADD_VEHICLES = "You are an admin and therefore cannot add vehicles!";

    private static final String YOU_ARE_NOT_THE_AUTHOR = "You are not the author of the comment you are trying to remove!";
    private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
    private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
    private final static String USER_HEADER = "--USER %s--";
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;

    //TODO

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Vehicle> vehicles;
    private UserRole userRole;
    public UserImpl(String username, String firstName, String lastName, String password, UserRole userRole){
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        this.userRole = userRole;
        switch (this.userRole){
            case ADMIN -> {
                isAdmin();
                break;
            }
            case NORMAL -> {
                vehicles = new ArrayList<Vehicle>(5);
                break;
            }
            case VIP -> {
                vehicles = new ArrayList<Vehicle>();
                break;
            }
        }
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    private void setUsername(String username){
        ValidationHelpers.validatePattern(username,USERNAME_REGEX_PATTERN,USERNAME_PATTERN_ERR);
        ValidationHelpers.validateStringLength(username,USERNAME_LEN_MIN,USERNAME_LEN_MAX,USERNAME_LEN_ERR);
        this.username = username;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateStringLength(firstName,FIRSTNAME_LEN_MIN,FIRSTNAME_LEN_MAX,FIRSTNAME_LEN_ERR);
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateStringLength(lastName,LASTNAME_LEN_MIN,LASTNAME_LEN_MAX,LASTNAME_LEN_ERR);
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    private void setPassword(String password) {
        ValidationHelpers.validatePattern(password,PASSWORD_REGEX_PATTERN,PASSWORD_PATTERN_ERR);
        ValidationHelpers.validateStringLength(password,PASSWORD_LEN_MIN,PASSWORD_LEN_MAX,PASSWORD_LEN_ERR);
        this.password = password;
    }

    @Override
    public UserRole getRole() {
        return this.userRole;
    }
    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (isAdmin()){
            throw new IllegalArgumentException(ADMIN_CANNOT_ADD_VEHICLES);
        }
        if (this.userRole == UserRole.NORMAL && vehicles.size()>=5){
            throw new IllegalArgumentException(NOT_AN_VIP_USER_VEHICLES_ADD);
        }
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)){
            vehicles.remove(vehicle);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addComment(Comment commentToAdd, Vehicle vehicleToAddComment) {
        if (vehicles.isEmpty() || vehicles.contains(vehicleToAddComment)){
            vehicleToAddComment.addComment(commentToAdd);
        }
        else {
            throw new IllegalArgumentException(NO_VEHICLES_HEADER);
        }
    }

    @Override
    public void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment) {
        if (!vehicles.contains(vehicleToRemoveComment)){
            throw new IllegalArgumentException(YOU_ARE_NOT_THE_AUTHOR);
        }
        vehicleToRemoveComment.removeComment(commentToRemove);
    }

    @Override
    public String printVehicles() {
        if (userRole == UserRole.ADMIN){
            throw new IllegalArgumentException("Admins can't have list with vehicles!");
        }
        StringBuilder stringBuilder = new StringBuilder("--USER " +getUsername() + "--" + System.lineSeparator());
        for (Vehicle vehicleItem : vehicles) {
            stringBuilder.append(vehicles.indexOf(vehicleItem)+1 + "." + vehicleItem.info() + System.lineSeparator());
            if (!vehicleItem.getComments().isEmpty()){
                stringBuilder.append("--COMMENTS--" + System.lineSeparator());

                for (Comment comments : vehicleItem.getComments()) {
                    stringBuilder.append(comments.getContent() + System.lineSeparator() + comments.getAuthor() +
                            ((vehicleItem.getComments().indexOf(comments)<vehicleItem.getComments().size())?System.lineSeparator():""));
                }
            }
            else {
                stringBuilder.append("--NO COMMENTS--" +
                        ((vehicles.indexOf(vehicleItem)+1 < vehicles.size())?System.lineSeparator():""));
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean isAdmin() {
        if (userRole == UserRole.ADMIN){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public String printUserInfo() {
        return String.format("Username: %s, FullName: %s %s, Role: %s",getUsername(),getFirstName(),getLastName(),getRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }
}