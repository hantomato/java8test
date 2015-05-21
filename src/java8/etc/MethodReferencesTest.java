package java8.etc;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.Set;
import java.util.HashSet;
 

/**
 * anonymous class, lambda, method reference example
 * 
 * 
 * @author nmj
 *
 */
public class MethodReferencesTest {
     
	public static class Person {
		String name;
		int age;

		public static List<Person> createRoster() {
			List<Person> data = new ArrayList<>();
			data.add(new Person("정우성", 39));
			data.add(new Person("최민수", 48));
			data.add(new Person("김수현", 31));
			data.add(new Person("박시우", 34));
			return data;
		}
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return name;
		}
		
		public Integer getBirthday() {
			return age;
		}
		
		public void printPerson() {
			System.out.println("Person - name : " + name + ", age :" + age);
		}
		
		public static int compareByAge(Person a, Person b) {
			return Integer.compare(a.getBirthday(), b.getBirthday());
		}
		
//		public string 
		
	}
	
	
    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(
            SOURCE sourceCollection, Supplier<DEST> collectionFactory) {
         
    		// 이건 타입이 틀린 collection으로 변환하는 거같음.ex) List -> Set 으로 변환.
            DEST result = collectionFactory.get();
            for (T t : sourceCollection) {
                result.add(t);
            }
            
            return result;
    }  
       
    public static void main(String... args) {
 
        List<Person> roster = Person.createRoster();        
 
        for (Person p : roster) {
            p.printPerson();
        }
      
         
        Person[] rosterAsArray = 
            roster.toArray(new Person[roster.size()]);
         
        class PersonAgeComparator
            implements Comparator<Person> {
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        
        
        //********** 각종 예제들 **********//
        
        ///////////////////////////////////
        // Without method reference
        Arrays.sort(rosterAsArray, new PersonAgeComparator());
        
        // or
        Arrays.sort(rosterAsArray, new Comparator<Person>() {							// anonymous class
			@Override
			public int compare(Person o1, Person o2) {
				return Integer.compare(o1.getBirthday(), o2.getBirthday());
			}
        });
        
        ///////////////////////////////////
        // With lambda expression
        Arrays.sort(rosterAsArray,														// lambda
            (Person a, Person b) -> {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        );
        
        // or
        Arrays.sort(rosterAsArray, (a,b) -> a.getBirthday().compareTo(b.getBirthday()));
         
        
        ///////////////////////////////////
        // With method reference														// method reference
        Arrays.sort(rosterAsArray, Person::compareByAge);		// 이것도 lambda 만큼 편하네
        
        
        ///////////////////////////////////
        // Reference to an instance method of a particular object
        class ComparisonProvider {
            public int compareByName(Person a, Person b) {
                return a.getName().compareTo(b.getName());
            }
         
            public int compareByAge(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);				// 이건 활용이 잘 안될거같네.
        
        
        ///////////////////////////////////
        // Reference to an instance method of an arbitrary object of a particular type
        Set<Person> rosterSetLambda = transferElements(roster, () -> { return new HashSet<>(); });		// 이 두 코드는 잘 모르겠음.
        Set<Person> rosterSet = transferElements(roster, HashSet::new);
        
        System.out.println("Printing rosterSet: 1");

        rosterSetLambda.stream().forEach(p -> p.printPerson());
        System.out.println("Printing rosterSet: 1");
        
        rosterSet.stream().forEach(p -> p.printPerson());
    }
}