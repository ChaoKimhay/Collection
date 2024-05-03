package Project;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
class Person{
    Scanner input = new Scanner(System.in);
    Integer id;
    String name;
    String gender;
    Integer age;

    Person(){}
    Person(int id, String name, String gender, int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    void input(){
        System.out.println("Input name : ");
        name = input.nextLine();
        System.out.println("Input gender : ");
        gender = input.nextLine();
        System.out.println("Input age : ");
        age = input.nextInt();
    }

    @Override
    public String toString() {
        return
                id +
                        "," + name +
                        "," + gender +
                        "," + age ;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> workers = new ArrayList<>();
//        workers.add(new Person(new Random().nextInt(10),"Wincent","Male",22));
//        workers.add(new Person(new Random().nextInt(10), "Dorado", "Female))",20));


        int option;
        do {
            System.out.println("\t 1. Write Object to file");
            System.out.println("\t 2. Read Object to file");
            System.out.println("\t 3. Exit");
            System.out.print("Input option : ");
            option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1: {
                    int n, num = 0;
                    System.out.print("Enter amount of data that want to input : ");
                    n = new Scanner(System.in).nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Input " + num + 1 + " data");
                        System.out.print("Input name : ");
                        String name = new Scanner(System.in).nextLine();
                        System.out.print("Input gender : ");
                        String gender = new Scanner(System.in).nextLine();
                        System.out.print("Input age : ");
                        int age = new Scanner(System.in).nextInt();
                        int id;
                        workers.add(new Person(new Random().nextInt(1, 10), name, gender, age));
                    }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("Data.csv", true));
                        for (Person person : workers) {
                            writer.write(person.toString());
                            writer.newLine();

                        }
                        System.out.println("Data written to Data.csv successfully.");
                        writer.close();

                    } catch (Exception ignore) {
                    }
                }
                break;
                case 2: {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("Data.csv"));
                        Table table = new Table(4, BorderStyle.CLASSIC);
                        for (int i = 0; i < 4; i++) {
                            table.setColumnWidth(i, 20, 20);
                        }
                        table.addCell("ID");
                        table.addCell("Name");
                        table.addCell("Gender");
                        table.addCell("Age");
                        String data;
                        while ((data = reader.readLine()) != null) {
                            String[] result = data.split(",");
                            table.addCell(result[0]);
                            table.addCell(result[1]);
                            table.addCell(result[2]);
                            table.addCell(result[3]);
                        }
                        System.out.println(table.render());
                    } catch (Exception ignore) {
                    }
                }
                break;
                case 3: {
                    System.out.println("Exiting...");
                }


            }
        } while (option != 3);
    }
}
