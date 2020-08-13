package consumables;

import modelo.Points;

public interface Consumable {

        public  void activate();

        public void useWithCorrectAnswer ();

        public void useWithIncorrectAnswer ();

        public void multiplicate(Points points);
}