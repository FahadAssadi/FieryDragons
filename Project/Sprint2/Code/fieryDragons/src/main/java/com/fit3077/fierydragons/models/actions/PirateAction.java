package com.fit3077.fierydragons.models.actions;

public class PirateAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("Executing pirate action");
        return false;
    }
}
