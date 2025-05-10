package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand{
    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository){
        super(vehicleDealershipRepository);
    }
    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        User user = getVehicleDealershipRepository().getLoggedInUser();
        if (user.getRole()!= UserRole.ADMIN){
            throw new IllegalArgumentException("You are not Admin!");
        }
        StringBuilder stringBuilder = new StringBuilder("--USERS--" + System.lineSeparator());
        for (User userItem : getVehicleDealershipRepository().getUsers()) {
            stringBuilder.append(getVehicleDealershipRepository().getUsers().indexOf(userItem)+1 + ". " + userItem.printUserInfo() +
                    ((getVehicleDealershipRepository().getUsers().indexOf(userItem)+1 < getVehicleDealershipRepository().getUsers().size()) ? System.lineSeparator() : ""));
        }
        return stringBuilder.toString();
    }
    //TODO
}