package java8.generic;

import java.util.ArrayList;

/**
 * generic widecard..
 * 
 * @author nmj
 *
 */
public class Test1 {

	public void print(String a) {
		
	}
	public void print(int a) {
		
	}
	
	public static void takeAnimals(ArrayList<Animal> animals) {
		for (Animal animal : animals) {
			animal.eat();
		}
		
		animals.add(new Tiger());
	}
	
	// ? extends Animal => Animal 을 상속받은 모든 서브 타입을 사용 가능.
	public static void takeAnimals2(ArrayList<? extends Animal> animals) {
		for (Animal animal : animals) {
			animal.eat();
		}
	}

	// 위와 같다.
	public static <T extends Animal> void takeAnimals3(ArrayList<T> animals) {
		for (Animal animal : animals) {
			animal.eat();
		}
	}
	
	public static void main(String... argc) {
		ArrayList<Animal> animals = new ArrayList<>();
		ArrayList<Human> humans = new ArrayList<>();
		ArrayList<Tiger> tigers = new ArrayList<>();
		ArrayList<Woman> womans = new ArrayList<>();
		
		takeAnimals(animals);
//		takeAnimals(humans);	// The method takeAnimals(ArrayList<Animal>) in the type Test1 is not applicable for the arguments (ArrayList<Human>)
		
		takeAnimals2(animals);
		takeAnimals2(humans);
		takeAnimals2(womans);
		
		takeAnimals3(animals);
		takeAnimals3(humans);
		takeAnimals3(womans);
	}
}
