package consumables;

import modelo.game.Points;

public interface Consumable {

        public  void activate();

        public void useWithCorrectAnswer ();

        public void useWithIncorrectAnswer ();

        public void multiplicate(Points points);
}