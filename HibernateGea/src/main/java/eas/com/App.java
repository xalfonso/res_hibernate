package eas.com;

import eas.com.dao.impl.AuthorDao;
import eas.com.entity.Author;

import java.util.List;
import java.util.Scanner;

/**
 * Created by eduardo on 11/7/2016.
 */
public class App {


    private static AuthorDao authorDao;


    public static void main(String[] args) {

        authorDao = new AuthorDao();
        try (Scanner scanner = new Scanner(System.in);) {

            String option = "";
            do {
                System.out.println("Options");
                System.out.println("1. Show Author");
                System.out.println("2. Save Author");
                System.out.println("3. Update Author");
                System.out.println("4. Delete Author");
                System.out.println("5. List of Author");
                int menu = scanner.nextInt();


                switch (menu) {
                    case 1:
                        getOption();
                        break;
                    case 2:
                        insertOption();
                        break;
                    case 3:
                        updateOption();
                        break;
                    case 4:
                        deleteOption();
                        break;
                    case 5:
                        listOption();
                        break;
                    default:
                        System.out.println("Option invalid");
                }

                System.out.print("Do you want to continue (y/n): ");
                option = scanner.nextLine();

            } while (option.equals("Y"));

        } catch (Exception e) {
            System.out.println("Error detected: " + e.getMessage());
        } finally {
            authorDao.close();
        }

    }


    public static void listOption() throws Exception {
        System.out.println("List of Authors");
        List<Author> authors = authorDao.getAll();
        for (Author author : authors) {
            System.out.println(author.toString());
        }

    }

    public static void updateOption() throws Exception {
        Author author = getOption();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write the first name (update): ");
        String firstName = scanner.nextLine();

        System.out.print("Write the second name (update): ");
        String secondName = scanner.nextLine();

        System.out.print("Write the first surname (update): ");
        String firstSurname = scanner.nextLine();

        System.out.print("Write the second surname (update): ");
        String secondSurname = scanner.nextLine();

        author.setFirstName(firstName)
                .setSecondName(secondName)
                .setFirstSurname(firstSurname)
                .setSecondSurname(secondSurname);

        authorDao.update(author);

        System.out.println("Author updated successfully");
    }


    public static void deleteOption() throws Exception {
        Author author = getOption();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you sure of delete this author (y/n)");
        String opt = scanner.nextLine().toUpperCase();

        switch (opt) {
            case "Y":
                System.out.println("Delete option selected");
                authorDao.delete(author.getId());
                break;
            case "N":
                System.out.println("Operation cancelled");
                break;
            default:
                System.out.println("Incorrect option");
        }
    }

    public static Author getOption() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write the id of author ");
        long id = scanner.nextLong();
        Author author = authorDao.get(id);

        System.out.println("Data Author");
        System.out.println(author.toString());

        return author;
    }


    public static void insertOption() throws Exception {
        System.out.println("Insert option selected");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Write the first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Write the second name: ");
        String secondName = scanner.nextLine();

        System.out.print("Write the first surname: ");
        String firstSurname = scanner.nextLine();

        System.out.print("Write the second surname: ");
        String secondSurname = scanner.nextLine();

        Author author = new Author(firstName, secondName, firstSurname, secondSurname);
        authorDao.insert(author);
    }
}
