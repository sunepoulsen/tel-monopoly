package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyScholarshipAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveBackwardAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveBackwardToFieldAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveForwardAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveForwardToFieldAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveToJailAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveToNextFerryAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveToNextShippingAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.PayAmountAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.PayHousingTaxes;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.ReceiveAmountAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.ReceiveFromAllPlayersAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCardPile;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.MonopolyChanceCard;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.MonopolyPrisonCard;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Representation of a single instance of a Monopoly game.
 */
@Data
public class MonopolyGame {
    /**
     * Instance factory.
     * <p>
     *     See <code>MonopolyInstanceFactory</code> for an explanation of its uses.
     * </p>
     */
    private MonopolyInstanceFactory instanceFactory;

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

    public MonopolyGame(MonopolyInstanceFactory instanceFactory) {
        this.instanceFactory = instanceFactory;
        this.players = new LinkedBlockingQueue<>();
        this.chanceCardPile = new ChanceCardPile();
        this.board = new MonopolyBoard();
        this.dices = new Dices();
    }

    public void configureGame() {
        configureChanceCards();
        configureBoard();
    }

    public void playTurnForNextPlayer() {
        Turn turn = instanceFactory.newTurnInstance(players.remove(), this.dices);
        MonopolyAction action = turn.playTurn();
        action.performAction(turn);

        this.players.add(turn.getPlayer());
    }

    private void configureChanceCards() {
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-start", "Ryk frem til 'START'", new MoveForwardToFieldAction("start-field")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-quarter-tax", "Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000", new ReceiveAmountAction(3000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-forward-3-fields", "Ryk 3 felter frem", new MoveForwardAction(3)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-next-shipping", "Ryk frem til nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", new MoveToNextShippingAction()));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-share-dividend", "Modtag udbytte af Deres aktier - kr. 1.000", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("pay-dentist-bill", "De har modtaget Deres tandlægeregning. Betal kr. 2.000", new PayAmountAction(2000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-from-each-player", "De skal holde familiefest og får et tilskud fra hver medspiller på kl. 500", new ReceiveFromAllPlayersAction(new ArrayList<>(this.players), 500.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("won-in-lottery", "De har vundet i KLasselotteriet. Modtag kr. 500", new ReceiveAmountAction(500.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-share-dividend", "De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("drawn-premium-bonds", "Deres præmieobligationer er udtrukket. De modtager kr. 1.000 af banken", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("ran-across-full-stop", "De har kørt frem for 'Fuldt stop'. Betal kr. 1.000 i bøde", new PayAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-strandvejen", "Ryk frem til Strandvejen. Hvis de passerer 'START', indkassér da kr. 4.000", new MoveForwardToFieldAction("strandvejen")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-from-joint-party", "De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaller alle straks. Modtag fra hver spiller kr. 500", new ReceiveFromAllPlayersAction(new ArrayList<>(this.players), 500.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("won-in-lottery", "De har vundet i KLasselotteriet. Modtag kr. 500", new ReceiveAmountAction(500.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("too-many-cigarettes", "De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200", new PayAmountAction(200.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("old-furnitures", "De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyPrisonCard("prison-card", "I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det eller De kan sælge det"));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-frederiksberg-allé", "Ryk frem til Frederiksberg Allé. Hvis de passerer 'START', indkassér da kr. 4.000", new MoveForwardToFieldAction("freberiksberg-allé")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-jail", "Gå i fængsel. Ryk direkte til fængsel. Selm om De passerer 'START', indkasserer De ikke kr. 4.000", new MoveToJailAction()));
        this.chanceCardPile.addCard(new MonopolyChanceCard("receive-share-dividend", "Modtag udbytte af Deres aktier - kr. 1.000", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("car-repair", "Betal kr. 3.000 for reparation af Deres vogn", new PayAmountAction(3000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-backward-3-fields", "Ryk 3 felter tilbage", new MoveBackwardAction(3)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("car-insurance", "Betal Deres bilforsikring - kr. 1.000", new PayAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-next-ferry", "Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer 'START', indkassér da kl. 4.000", new MoveToNextFerryAction()));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-frederiksberg-allé", "Ryk frem til Rådhuspladsen.", new MoveForwardToFieldAction("rådhuspladsen")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("yeild-own-breeding", "Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken", new ReceiveAmountAction(200.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-jail", "Gå i fængsel. Ryk direkte til fængsel. Selm om De passerer 'START', indkasserer De ikke kr. 4.000", new MoveBackwardToFieldAction("prison")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("won-lottery", "De havde en række med elleve rigtige i tipning. Modtag kr. 1.000", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("parking-fine", "De har fået en parkeringsbøde. Betal kr. 200 i bøde", new PayAmountAction(200.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("salary-raise", "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-backward-3-fields", "Ryk 3 felter tilbage", new MoveBackwardAction(3)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("property-taxes", "Ejendomsskatterne er steget. Ekstraudgifterne er: kr. 800 pr. hus. kr. 2.300 pr. hotel", new PayHousingTaxes(800.0, 2300.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("car-wash", "Betal for vognvask og smøring", new PayAmountAction(300.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-start", "Ryk frem til 'START'", new MoveForwardToFieldAction("start-field")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("car-repair", "Betal kr. 3.000 for reparation af Deres vogn", new PayAmountAction(3000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("car-tire", "De har købt 4 nye dæk til Deres vogn. Betal kr. 1.000", new PayAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("player-birthday", "Det er Deres fødselsdag. Modtag af hver medspiller på kr. 200", new ReceiveFromAllPlayersAction(new ArrayList<>(this.players), 200.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("oil-taxes", "Oliepriserne er steget og De skal betale: kr. 500 pr. hus. kr. 2.000 pr. hotel", new PayHousingTaxes(500.0, 2000.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-next-shipping", "Ryk frem til nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", new MoveToNextShippingAction()));
        this.chanceCardPile.addCard(new MonopolyChanceCard("beer", "Betal kr. 200 for levering af 2 kasser øl", new PayAmountAction(200.0)));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-grønningen", "Ryk frem til Grønningen. Hvis de passerer 'START', indkassér da kr. 4.000", new MoveForwardToFieldAction("grønningen")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("drawn-premium-bonds", "Deres præmieobligationer er udtrukket. De modtager kr. 1.000 af banken", new ReceiveAmountAction(1000.0)));
        this.chanceCardPile.addCard(new MonopolyPrisonCard("prison-card", "I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares, indtil De får brug for det eller De kan sælge det"));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-mols-linien", "Ryk frem til Mols-Linien. Hvis de passerer 'START', indkassér da kr. 4.000", new MoveForwardToFieldAction("mols-linien")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("move-to-vimmelskaftet", "Ryk frem til Vimmelskaftet. Hvis de passerer 'START', indkassér da kr. 4.000", new MoveForwardToFieldAction("vimmelskaftet")));
        this.chanceCardPile.addCard(new MonopolyChanceCard("monopoly-scholarship", "De modtager 'Matador-legatet for værdigt trængende' på kr. 40.000. Ved værdigt trængende forstås, at Deres formue, dvs. Deres kontante penge + skøder + bygning, ikke overstiger kr. 15.000", new MonopolyScholarshipAction()));
    }

    private void configureBoard() {
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

        this.board.insertField(new ActionField("goto-prison", new MoveToJailAction()));

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
