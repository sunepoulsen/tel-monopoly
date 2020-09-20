package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCardPile;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.ActionField;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.ChanceField;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.GroundField;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.PrisonField;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.GroundGroup;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.HousingGround;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.ShippingGround;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.UtilityGround;
import lombok.Data;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Representation of a single instance of a Monopoly game.
 */
@Data
public class MonopolyGame {
    /**
     * Contains a list of players in the game.
     * <p>
     * The list is sorted by the order that the players are playing at each turn.
     * </p>
     */
    private Queue<Player> players;

    /**
     * Pile of chance cards.
     */
    private ChanceCardPile chanceCardPile;

    /**
     * Instance of the game board that holds the state of the entire board.
     */
    private MonopolyBoard board;

    /**
     * Dices for the game.
     */
    private Dices dices;

    public MonopolyGame() {
        this.players = new LinkedBlockingQueue<>();
        this.chanceCardPile = new ChanceCardPile();
        this.board = new MonopolyBoard();
        this.dices = new Dices();
    }

    public void configureBoard() {
        GroundGroup groundGroup;

        GroundGroup shippingGroup = new GroundGroup("shipping");
        GroundGroup utilityGroup = new GroundGroup("utilities");

        groundGroup = new GroundGroup("blue");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "rødovrevej", 1200.0, 600.0, 1000.0, Arrays.asList(50.0, 250.0, 750.0, 2250.0, 4000.0, 6000.0))));
        this.board.insertField(new ChanceField(groundGroup.getId(), this.chanceCardPile));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "hvidovrevej", 1200.0, 600.0, 1000.0, Arrays.asList(50.0, 250.0, 750.0, 2250.0, 4000.0, 6000.0))));

        this.board.insertField(new ActionField("income-tax", new NoAction()));
        this.board.insertField(new GroundField(new ShippingGround(shippingGroup, "scandlines-helsingør-helsingborg")));

        groundGroup = new GroundGroup("orange");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "roskildevej", 2000.0, 1000.0, 1000.0, Arrays.asList(100.0, 600.0, 1800.0, 5400.0, 8000.0, 11000.0))));
        this.board.insertField(new ChanceField(groundGroup.getId(), this.chanceCardPile));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "valby-langgade", 2000.0, 1000.0, 1000.0, Arrays.asList(100.0, 600.0, 1800.0, 5400.0, 8000.0, 11000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "allégade", 2400.0, 1200.0, 1000.0, Arrays.asList(150.0, 800.0, 2000.0, 6000.0, 9000.0, 12000.0))));

        this.board.insertField(new PrisonField());

        groundGroup = new GroundGroup("green");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "freberiksberg-allé", 2800.0, 1400.0, 2000.0, Arrays.asList(200.0, 1000.0, 3000.0, 9000.0, 12500.0, 15000.0))));
        this.board.insertField(new GroundField(new UtilityGround(utilityGroup, "tuborg-squach")));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "bülowsvej", 2800.0, 1400.0, 2000.0, Arrays.asList(200.0, 1000.0, 3000.0, 9000.0, 12500.0, 15000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "gl-kongevej", 3200.0, 1600.0, 2000.0, Arrays.asList(250.0, 1250.0, 3750.0, 10000.0, 14000.0, 18000.0))));

        this.board.insertField(new GroundField(new ShippingGround(shippingGroup, "mols-linien")));

        groundGroup = new GroundGroup("grå");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "bernstoffsvej", 3600.0, 1800.0, 2000.0, Arrays.asList(300.0, 1400.0, 4000.0, 11000.0, 15000.0, 19000.0))));
        this.board.insertField(new ChanceField(groundGroup.getId(), this.chanceCardPile));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "hellerupvej", 3600.0, 1800.0, 2000.0, Arrays.asList(300.0, 1400.0, 4000.0, 11000.0, 15000.0, 19000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "strandvejen", 4000.0, 2000.0, 2000.0, Arrays.asList(350.0, 1600.0, 4400.0, 12000.0, 16000.0, 20000.0))));

        this.board.insertField(new ActionField("parkering", new NoAction()));

        groundGroup = new GroundGroup("rød");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "trianglen", 4400.0, 2200.0, 3000.0, Arrays.asList(350.0, 1800.0, 5000.0, 14000.0, 17500.0, 21000.0))));
        this.board.insertField(new ChanceField(groundGroup.getId(), this.chanceCardPile));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "østerbrogade", 4400.0, 2200.0, 3000.0, Arrays.asList(350.0, 1800.0, 5000.0, 14000.0, 17500.0, 21000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "grønningen", 4800.0, 2400.0, 3000.0, Arrays.asList(400.0, 2000.0, 6000.0, 15000.0, 18500.0, 22000.0))));

        this.board.insertField(new GroundField(new ShippingGround(shippingGroup, "scandlines-gedser-rostock")));

        groundGroup = new GroundGroup("hvid");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "bredgade", 5200.0, 2600.0, 3000.0, Arrays.asList(450.0, 2200.0, 6600.0, 16000.0, 19500.0, 23000.0))));
        this.board.insertField(new GroundField(new UtilityGround(utilityGroup, "coca-cola")));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "kgs-nytorv", 5200.0, 2600.0, 3000.0, Arrays.asList(450.0, 2200.0, 6600.0, 16000.0, 19500.0, 23000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "østergade", 5600.0, 2800.0, 3000.0, Arrays.asList(500.0, 2400.0, 7200.0, 17000.0, 20500.0, 24000.0))));

        this.board.insertField(new ActionField("goto-prison", new NoAction()));

        groundGroup = new GroundGroup("gul");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "amagertorv", 6000.0, 3000.0, 4000.0, Arrays.asList(550.0, 2600.0, 7800.0, 18000.0, 22000.0, 25000.0))));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "vimmelskaftet", 6000.0, 3000.0, 4000.0, Arrays.asList(550.0, 2600.0, 7800.0, 18000.0, 22000.0, 25000.0))));
        this.board.insertField(new ChanceField(groundGroup.getId(), this.chanceCardPile));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "nygade", 6400.0, 3200.0, 4000.0, Arrays.asList(600.0, 3000.0, 9000.0, 20000.0, 24000.0, 28000.0))));

        this.board.insertField(new GroundField(new ShippingGround(shippingGroup, "scandlines-rødby-puttgarden")));
        this.board.insertField(new ChanceField("bordeauxrød", this.chanceCardPile));

        groundGroup = new GroundGroup("bordeauxrød");
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "frederiksberggade", 7000.0, 3500.0, 4000.0, Arrays.asList(700.0, 3500.0, 10000.0, 22000.0, 26000.0, 30000.0))));
        this.board.insertField(new ActionField("government-tax", new NoAction()));
        this.board.insertField(new GroundField(new HousingGround(groundGroup, "rådhuspladsen", 8000.0, 4000.0, 4000.0, Arrays.asList(1000.0, 4000.0, 12000.0, 28000.0, 34000.0, 40000.0))));
    }

}
