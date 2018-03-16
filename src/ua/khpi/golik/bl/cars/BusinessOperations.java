package ua.khpi.golik.bl.cars;

import java.util.ArrayList;

public interface BusinessOperations {
	
	ArrayList<AnyCar> normalSortByPrice(ArrayList<AnyCar> carList);
	
	ArrayList<AnyCar> normalSortByName(ArrayList<AnyCar> carList);
	
	ArrayList<AnyCar> reverseSortByPrice(ArrayList<AnyCar> carList);
	
	ArrayList<AnyCar> reverseSortByName(ArrayList<AnyCar> carList);
}
