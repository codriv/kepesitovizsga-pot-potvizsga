package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class Zoo {

    private Set<ZooAnimal> animals = new HashSet<>();
    private MariaDbDataSource dataSource;

    public Zoo() {
    }

    public Zoo(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Set<ZooAnimal> getAnimals() {
        return new HashSet<>(animals);
    }

    public void addAnimal(ZooAnimal animal) {
        animals.add(animal);
    }

    public ZooAnimal getHeaviestAnimalInTheZoo() {
        return animals.stream().max(Comparator.comparing(ZooAnimal::getWeight)).orElseThrow(()->new IllegalStateException("Animal not found!"));
    }

    public long countWeights() {
        return animals.stream().mapToLong(ZooAnimal::getWeight).sum();
    }

    public ZooAnimal findExactAnimal(ZooAnimal animal) {
        List<ZooAnimal> animalsFound = animals.stream().filter(animal::equals).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new IllegalArgumentException("There is no such animal in the zoo!");
        } else {
            return animalsFound.get(0);
        }
    }

    public ZooAnimal findExactAnimalByName(String name) {
        List<ZooAnimal> animalsFound = animals.stream().filter(animal -> name.equals(animal.getName())).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new IllegalArgumentException("There is no such animal in the zoo!");
        } else {
            return animalsFound.get(0);
        }
    }

    public int getNumberOfAnimals() {
        return animals.size();
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        return animals.stream().sorted(Comparator.comparing(ZooAnimal::getName)).collect(Collectors.toList());
    }

    public Map<AnimalType, Integer> getAnimalStatistics() {
        Map<AnimalType, Integer> statistic = new HashMap<>();
        for (AnimalType type: AnimalType.values()) {
            int num = (int) animals.stream().filter(animal -> type == animal.getType()).count();
            statistic.put(type, num);
        }
        return statistic;
    }

    public void loadAnimals() {
        try(Connection con = dataSource.getConnection();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from animals")) {
                while (rs.next()) {
                    processResultSet(rs);
                }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach database!");
        }
    }

    private void processResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("animal_name");
        int length = rs.getInt("length_of_member");
        long weight = rs.getLong("weight");
        AnimalType type = AnimalType.valueOf(rs.getString("animal_type"));
        switch (type) {
            case LION : animals.add(new Lion(name));
                break;
            case GIRAFFE : animals.add(new Giraffe(name, length));
                break;
            case ELEPHANT : animals.add(new Elephant(name, length, weight));
        }
    }
}