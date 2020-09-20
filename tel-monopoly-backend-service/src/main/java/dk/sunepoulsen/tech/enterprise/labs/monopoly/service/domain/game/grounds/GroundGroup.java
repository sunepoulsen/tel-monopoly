package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class GroundGroup {
    @Getter
    private final String id;
    private final Set<Ground> grounds;

    public GroundGroup(String id) {
        this.id = id;
        this.grounds = new HashSet<>();
    }

    public void addGround(Ground ground) {
        this.grounds.add(ground);
    }
}
