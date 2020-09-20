package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import lombok.Data;
import lombok.Getter;

@Data
public class Ground {
    @Getter
    private String id;
    private GroundGroup group;

    private Double price;
    private Double mortgage;
    private Player owner;

    public Ground() {
    }

    public Ground(GroundGroup group, String id, Double price, Double mortgage) {
        this.group = group;
        this.group.addGround(this);
        this.id = id;
        this.price = price;
        this.mortgage = mortgage;
    }
}
