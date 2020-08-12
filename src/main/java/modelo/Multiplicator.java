package modelo;

import java.util.List;

public abstract class Multiplicator {
    protected Integer factor;

    public void multiplicate(Points points) {points.multiplicate(this.factor);}

    public void deactivate() {this.factor = 0;}

    public void activate(){}
}