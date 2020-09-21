package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    public Clorus(double e) {
        super("clorus");
        this.energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Creature replicate() {
        this.energy = this.energy()/2;
        return new Clorus(this.energy);
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plips = new ArrayDeque<>();
        boolean anyPlip = false;
        for (Direction d: new Direction[]{Direction.BOTTOM, Direction.LEFT, Direction.TOP, Direction.RIGHT}) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            }
            else if (neighbors.get(d).name().equals("plip")){
                plips.add(d);
                anyPlip = true;
            }
        }

        //Rule 1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        else if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plips));
        }

        else if (this.energy() >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        else {
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

    }

    @Override
    public double energy() {
        return super.energy();
    }

    @Override
    public String name() {
        return super.name();
    }

    @Override
    public Color color() {
        return new Color(34, 0, 231);
    }
}
