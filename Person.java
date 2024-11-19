import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    String name;
    List<Person> children;
    Person parent1; //Allows for representation of families with two parents.
    Person parent2;

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public void printTree(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
        System.out.println(name);
        for (Person child : children) {
            child.printTree(indent + 1);
        }
    }
}

public class FamilyTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person root = new Person("Ancestor"); //Root of the family tree

        while (true) {
            System.out.println("\n--- Tree List---");
            System.out.println("1. Add Tree Node");
            System.out.println("2. Remove Tree Node");
            System.out.println("3. Display Tree");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Person's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter parent 1's name (or leave blank if none): ");
                    String parent1Name = scanner.nextLine();
                    System.out.print("Enter parent 2's name (or leave blank if none): ");
                    String parent2Name = scanner.nextLine();
                    addPerson(root, name, parent1Name, parent2Name);
                    break;
                case 2:
                    root.printTree(0);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    //Helper function to find a person by name (Could be significantly improved for larger trees)
    static Person findPerson(Person root, String name) {
        if (root.name.equals(name)) return root;
        for (Person child : root.children) {
            Person found = findPerson(child, name);
            if (found != null) return found;
        }
        return null;
    }

    static void addPerson(Person root, String name, String parent1Name, String parent2Name) {
        Person newPerson = new Person(name);
        Person parent1 = (parent1Name.isEmpty()) ? null : findPerson(root, parent1Name);
        Person parent2 = (parent2Name.isEmpty()) ? null : findPerson(root, parent2Name);

        if (parent1 != null) parent1.addChild(newPerson);
        if (parent2 != null) parent2.addChild(newPerson);
        // Assign parents to the new person
        newPerson.parent1 = parent1;
        newPerson.parent2 = parent2;

    }
}
