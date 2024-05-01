package com.fit3077.fierydragons.models.actions;

public class MoveAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("Executing move action");
        return false;
    }
}
