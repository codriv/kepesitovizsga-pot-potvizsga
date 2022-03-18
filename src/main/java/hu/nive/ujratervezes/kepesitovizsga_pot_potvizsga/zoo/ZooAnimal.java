package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

import java.util.Objects;

public abstract class ZooAnimal {

    protected String name;
    protected int length;
    protected long weight;
    protected AnimalType type;


    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public long getWeight() {
        return weight;
    }

    public AnimalType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooAnimal animal = (ZooAnimal) o;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
