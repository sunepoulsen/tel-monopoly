package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.Ground;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ByeGroundReason implements ChooseActionReason {
    @Getter
    private final Ground ground;

    public ByeGroundReason(Ground ground) {
        this.ground = ground;
    }

    @Override
    public List<MonopolyAction> possibleActions() {
        return Arrays.asList(new ByeGroundAction(this.ground), new NoAction());
    }
}
