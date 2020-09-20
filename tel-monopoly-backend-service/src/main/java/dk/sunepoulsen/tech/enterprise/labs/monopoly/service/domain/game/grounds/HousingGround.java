package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HousingGround extends Ground {
    private Double housingCost;
    private List<Double> rents;

    public HousingGround(GroundGroup group, String id, Double price, Double mortgage, Double housingCost, List<Double> rents) {
        super(group, id, price, mortgage);
        this.housingCost = housingCost;
        this.rents = rents;
    }
}
