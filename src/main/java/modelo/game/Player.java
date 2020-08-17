package modelo.game;

import exceptions.NoMoreConsumablesException;
import modelo.consumables.*;
import static constantes.ErrorMessage.PLAYER_HAS_NO_MORE_CONSUMABLES_ERROR;

public class Player {
    private String  name;
    private Integer points = 0;
    private Integer consumablesAmount = 2;

    public Player() {}

    public Player(String name) {
        this.setName(name);
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void gainAmountOfPoints(Integer points) {
        this.points += points;
        if (this.points < 0) {
            this.points = 0;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void activateConsumable(Consumable consumable) throws NoMoreConsumablesException {
        consumablesAmount--;
        if (consumablesAmount < 0)
            throw new NoMoreConsumablesException(PLAYER_HAS_NO_MORE_CONSUMABLES_ERROR);
        consumable.activate();
    }
}
