package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

public class Lion extends ZooAnimal{

    public Lion(String name) {
        this.name = name;
        type = AnimalType.LION;
    }

    public String getName() {
        return name;
    }
}
