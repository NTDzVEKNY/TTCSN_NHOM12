package btl_GA_TSP;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	ArrayList<City> cities = new ArrayList<>();
    private double fitness = 0;
    private double distance = 0;

    public Tour(ArrayList<City> cities) {
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
    }

    public double getDistance() {
        if (distance == 0) {
            for (int i = 0; i < cities.size(); i++) {
                City from = cities.get(i);
                City to = cities.get((i + 1) % cities.size());
                distance += from.distanceTo(to);
            }
        }
        return distance;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }
    public void printTour() {
        for (City city : cities) {
            System.out.print("City(" + city.getX() + ", " + city.getY() + ") -> ");
        }
        System.out.println("END"); // Để chỉ ra kết thúc lộ trình
    }
}
