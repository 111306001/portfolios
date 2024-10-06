import java.util.ArrayList;

public class Lab9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dogA = new Dog("Ruka", 0, "Shiba");
		Dog dogB = new Dog("Maya", 1, "Samoyed");
		Dog dogC = new Dog("Happy", 0, "Corgi");
		
		Bird birdA = new Bird("Kiwi", 0, false);
		Bird birdB = new Bird("Chuchu", 1, true);
		Bird birdC = new Bird("Tasty", 1, false);
		
		ArrayList <Dog> dogs = new ArrayList<Dog>();
		ArrayList <Bird> birds = new ArrayList<Bird>();
		dogs.add(dogA);
		dogs.add(dogB);
		dogs.add(dogC);
		birds.add(birdA);
		birds.add(birdB);
		birds.add(birdC);
		
		System.out.println("Doggies names:");
		for(Dog dog : dogs) {
			System.out.print(dog.getName() + " ");
		}
		System.out.println();
		System.out.println("Birdies names:");
		for(Bird bird : birds) {
			System.out.print(bird.getName() + " ");
		}
		System.out.println("\n");
		System.out.println("Comparison of dogA & birdA's breathe result:");
		dogA.breathe();
		birdA.breathe();
		System.out.println();
		System.out.println("Make all the dogs run and bark:");
		for(Dog dog : dogs) {
			dog.run();
			dog.bark();
		}
		System.out.println();
		System.out.println("Make all the bird fly:");
		for(Bird bird : birds) {
			bird.fly();
		}
		System.out.println();
		System.out.println("All the dog breeds:");
		for(Dog dog : dogs) {
			System.out.print(dog.getBreed() + " ");
		}
		System.out.println();
		System.out.println("All the unflyable birds:");
		for(Bird bird : birds) {
			if(bird.getFlyable() == false) {
				System.out.print(bird.getName() + " ");
			}
		}
		
	}

}
