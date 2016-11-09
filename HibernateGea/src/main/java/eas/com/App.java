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
                System.out.println("6. List of Author Like First Surname");
                System.out.println("7. Change Surname");
                System.out.println("8. Delete Author by First Name");
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
                    case 6:
                        listLikeSurnameOption();
                        break;
                    case 7:
                        changeSecondSurnameOption();
                        break;
                    case 8:
                        deleteAuthorByFirstNameOption();
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


    private static void listOption() throws Exception {
        System.out.println("List of Authors");
        List<Author> authors = authorDao.getAll();
        for (Author author : authors) {
            System.out.println(author.toString());
        }

    }

    private static void listLikeSurnameOption() throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("List of Authors like surname");
            System.out.print("Write the first surname (for like): ");
            String firstSurname = scanner.nextLine();

            List<Author> authors = authorDao.getLikeFirstSurname(firstSurname);
            for (Author author : authors) {
                System.out.println(author.toString());
            }
        }

    }


    private static void changeSecondSurnameOption() throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Write the second surname (to find): ");
            String oldSecondSurname = scanner.nextLine();

            System.out.print("Write the second surname (to update): ");
            String newSecondSurname = scanner.nextLine();

            if(authorDao.changeSecondSurname(newSecondSurname, oldSecondSurname)){
                System.out.println("Author second surname was  updated successfully");
            }else {
                System.out.println("The update was not made");
            }
        }
    }

    private static void deleteAuthorByFirstNameOption() throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Write the first name : ");
            String firstName = scanner.nextLine();

            if(authorDao.deleteAuthorByFirstName(firstName)){
                System.out.println("Author was deleted successfully");
            }else {
                System.out.println("The deleting was not made");
            }
        }
    }

    private static void updateOption() throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            Author author = getOption();
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
    }


    private static void deleteOption() throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            Author author = getOption();

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
    }

    private static Author getOption() throws Exception {
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("Write the id of author ");
            long id = scanner.nextLong();
            Author author = authorDao.get(id);

            System.out.println("Data Author");
            System.out.println(author.toString());

            return author;
        }
    }


    private static void insertOption() throws Exception {


        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Insert option selected");
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
}
