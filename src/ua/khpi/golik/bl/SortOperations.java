package ua.khpi.golik.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import ua.khpi.golik.bl.cars.AnyCar;

public class SortOperations {
	
	public static ArrayList<AnyCar> normalSortByPrice(ArrayList<AnyCar> carList) {
		Collections.sort(carList, new NormalPriceComparator());
		return carList;
	}
	
	public static ArrayList<AnyCar> normalSortByName(ArrayList<AnyCar> carList) {
		Collections.sort(carList, new NormalNameComparator());
		return carList;
	}
	
	public static ArrayList<AnyCar> reverseSortByPrice(ArrayList<AnyCar> carList) {
		Collections.sort(carList, new ReversePriceComparator());
		return carList;
	}
	
	public static ArrayList<AnyCar> reverseSortByName(ArrayList<AnyCar> carList) {
		Collections.sort(carList, new ReverseNameComparator());
		return carList;
	}
	
	public static ArrayList<AnyCar> choiceForMark(ArrayList<AnyCar> carList, String carMark) {
		Iterator<AnyCar> iterator = carList.iterator();
		ArrayList<AnyCar> newList = new ArrayList<>();
		while(iterator.hasNext()) {
			if(iterator.next().getCarMark().equals(carMark)) {
				newList.add(iterator.next());
			}
		}
		return newList;
	}
	
	public static ArrayList<AnyCar> choiceForClass(ArrayList<AnyCar> carList, String carClass) {
		Iterator<AnyCar> iterator = carList.iterator();
		ArrayList<AnyCar> newList = new ArrayList<>();
		while(iterator.hasNext()) {
			if(iterator.next().getCarClassEN().equals(carClass)) {
				newList.add(iterator.next());
			}
		}
		return newList;
	}
}


class NormalPriceComparator implements Comparator<AnyCar> {

	@Override
	public int compare(AnyCar o1, AnyCar o2) {
		int firstPrice = o1.getFrom10to30DaysPrice();
		int secondPrice = o2.getFrom10to30DaysPrice();
		if(firstPrice > secondPrice) {
			return 1;
		} else if(firstPrice < secondPrice) {
			return -1;
		} else {
			return 0;
		}
	}
}

class NormalNameComparator implements Comparator<AnyCar> {

	@Override
	public int compare(AnyCar o1, AnyCar o2) {
		String firstCarName = o1.getCarName();
		String secondCarName = o2.getCarName();
		if(firstCarName.compareTo(secondCarName) > 0) {
			return 1;
		} else if(firstCarName.compareTo(secondCarName) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}

class ReversePriceComparator implements Comparator<AnyCar> {

	@Override
	public int compare(AnyCar o1, AnyCar o2) {
		int firstPrice = o1.getFrom10to30DaysPrice();
		int secondPrice = o2.getFrom10to30DaysPrice();
		if(firstPrice > secondPrice) {
			return -1;
		} else if(firstPrice < secondPrice) {
			return 1;
		} else {
			return 0;
		}
	}
}

class ReverseNameComparator implements Comparator<AnyCar> {

	@Override
	public int compare(AnyCar o1, AnyCar o2) {
		String firstCarName = o1.getCarName();
		String secondCarName = o2.getCarName();
		if(firstCarName.compareTo(secondCarName) > 0) {
			return -1;
		} else if(firstCarName.compareTo(secondCarName) < 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
