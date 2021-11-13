package by.KuvonchbekN;

import by.KuvonchbekN.model.*;
import by.KuvonchbekN.service.*;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    //---------------the project------------------
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserService();
    static CourseService courseService = new CourseService();
    static CardService cardService = new CardService();
    static CategoryService categoryService = new CategoryService();
    static Owner owner = new Owner();

    public static void main(String[] args) {
	// write your code here
        System.out.println("=====================================================================================================");
        System.out.println("=====================================================================================================");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                  WELCOME TO UDEMY ONLINE                                        ==");
        System.out.println("==                                          PLATFORM                                               ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("==                                                                                                 ==");
        System.out.println("=====================================================================================================");
        System.out.println("=====================================================================================================");
        int stepCode;
        do {
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      ++            1.[LOGIN]\t    2.[SIGN UP]\t             ++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      ++                                                     ++");
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1 -> {
                    login();
                }
                case 2 -> {
                    System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      ++     1.[SIGNUP AS TEACHER]\t 2.[SIGNUP AS STUDENT]   ++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      ++                                                     ++");
                    System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.print("                        Choose your choice:  ");
                    int opt = scannerInt.nextInt();
                    if(opt == 1) signUp(UserRole.TEACHER);
                    else if(opt == 2) signUp(UserRole.STUDENT);
                }
            }
        }while(stepCode != 0);

    }

    // Login menu

    protected static void login(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.print("Enter your phone number: ");
        String phoneNumber = scannerStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scannerStr.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------");

        User user = userService.getUser(phoneNumber, password);

        if(user != null){
            if(user.getRole().equals(UserRole.ADMIN)) adminMenu(user);
            else if(user.getRole().equals(UserRole.TEACHER)) teacherMenu(user);
            else if(user.getRole().equals(UserRole.STUDENT)) studentMenu(user);
        }
        else{
            System.out.println("Incorrect password or phone number. Please try again!");
        }

    }

    ///============================ SignUp Menu =========================================

    protected static void signUp(UserRole role){
        System.out.print("Enter phone number: ");
        String phoneNumber = scannerStr.nextLine();
        User user = null;
        if(userService.check(phoneNumber) == null){
            int userCode;
            int code;
           int cnt = 0;
            do{
                code = userService.sendSmsCode();
                System.out.println("We Send code to " + phoneNumber + " phone number.\nPlease enter the code: " + code);
                System.out.print("Your code:  ");
                userCode = scannerInt.nextInt();
                if(userCode != code){
                    System.out.println("Incorrect sms code!");
                }
                cnt++;
            }while(cnt < 2 && code != userCode);

            if(code == userCode){
                // ============== Add User ===============================
                System.out.print("Enter your name: ");
                String name = scannerStr.nextLine();
                System.out.print("Enter password: ");
                String password = scannerStr.nextLine();
                 user = userService.add(new User(name, phoneNumber, password, role));

                // ============== Add Card ===============================
                System.out.println("-----------------------------------------------------------------");
                System.out.print("Enter your card number: ");
                String cardNumber = scannerStr.nextLine();
                System.out.print("Enter your card name: ");
                String cardName = scannerStr.nextLine();
                cardService.add(new Card(name, user.getId(), cardNumber, true));
            }
        }
        else{
            System.out.println("This number already exists!");
        }

        if(user != null){
            if(user.getRole().equals(UserRole.STUDENT)) studentMenu(user);
            else if(user.getRole().equals(UserRole.TEACHER)) teacherMenu(user);
        }
    }

    ///============================ Admin Menu =========================================

    protected static void adminMenu(User user){
        System.out.println("                                                                                            ");
        System.out.println("--------------------------------------------------------------------------------------------");
        int stepCode = 0;
        do{
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++       1.[ADD CATEGORY]\t 2.[GET COURSES]\t 3.[GET USERS]\t 4.[SET COMMISSION]\t 5.[BALANCE]\t 0.[EXIT]       ++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++                                                                                                              ++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("           Choose your choice:  ");
            stepCode = scannerInt.nextInt();

            switch (stepCode){
                ///###################
                ///[ADD CATEGORY]
                case 1 -> {
                    addCategoryMenu();
                }
                ///###################
                ///[GET COURSES]
                case 2 -> {
                        int ind = 1;
                        List<Course> courses = courseService.list(null);

                        for (Course course: courses){
                            System.out.println((ind++) + ". Course Name: " + course.getName());
                        }
                        ind = scannerInt.nextInt();

                        if(ind > 0 && ind <= courses.size()){
                            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      ++   1.[COURSE INFO]\t 2.[ENROLLED STUDENTS]\t 0.[EXIT]  ++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      ++                                                       ++");
                            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("                        Choose your choice:  ");
                            Course course = courses.get(ind - 1);
                            int option = scannerInt.nextInt();

                            if(option == 1) {
                                System.out.println("Course name: " + course.getName());
                                System.out.println("Course price: " + course.getPrice());
                                System.out.println("Course discount: " + course.getDiscount());
                                System.out.println("Teacher name: " + course.getTeacherName());
                                System.out.println("Teacher phone number: " + course.getTeacherPhoneNumber());
                                System.out.println("Number of enrolled students: " + course.getNumberOfEnrolledStudents());
                                if(course.isActive())
                                    System.out.println("Course status: Available");
                                else
                                    System.out.println("Course status: Unavailable");

                            }
                            else if(option == 2){
                                int j = 1;
                                for(User user1: StudentCourseService.getStudents(course)){
                                    System.out.println("------------" + (j++) + "--------------------");
                                    System.out.println("Name: " + user1.getName());
                                    System.out.println("Phone number: " + user1.getPhoneNumber());
                                    System.out.println("---------------------------------------------");
                                }
                            }
                        }
                        else System.out.println("Incorrect option");
                }
                case 3 -> {
                    int ind = 1;
                    List<User> users = userService.list(null);

                    for (User user1: users){
                        System.out.println((ind++) + ". User Name: " + user1.getName());
                    }
                    ind = scannerInt.nextInt();

                    if(ind > 0 && ind <= users.size()){
                        System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      ++    1.[USER INFO]\t 2.[ENROLLED COURSES]\t 0.[EXIT]    ++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      ++                                                       ++");
                        System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                        User user1 = users.get(ind - 1);
                        int option = scannerInt.nextInt();
                        System.out.print("                        Choose your choice:  ");

                        if(option == 1) {
                            System.out.println("Student name: " + user1.getName());
                            System.out.println("Phone : " + user1.getPhoneNumber());
                        }

                        else if(option == 2){
                            int j = 1;
                            for(Course course: StudentCourseService.getCourses(user1)){
                                System.out.println("------------" + (j++) + "--------------------");
                                System.out.println("Name: " + course.getName());
                                System.out.println("Teacher name: " + course.getTeacherName());
                                System.out.println("---------------------------------------------");
                            }
                        }
                    }
                    else System.out.println("Incorrect option");
                }
                case 4 -> {
                    //########################################
                    //###  SET COMMISSION (CardService)
                    System.out.println("Current commission percent is: " + owner.getCommission() * 100);
                    System.out.print("Enter commission in percentage(40): ");
                    owner.setCommission(scannerInt.nextDouble() / 100);
                }
                case 5 ->{
                    //########################################
                    //###  SEE BALANCE (CardService)
                    System.out.println(owner.getBalance());
                }
            }
        }while(stepCode != 0);
    }


    ///============================ Admin Menu =========================================


    ///##############################################################################
    ///Adding Category Menu
    public static void addCategoryMenu(){

        UUID parentId = null;
        int ind = 1;
        do {
            ind = 1;
            List<Category> categoryList = categoryService.getCategoryList(parentId);
            for (Category category: categoryList) {
                System.out.println((ind++) + ". " + category.getName());
            }
            System.out.println(ind + ".[ADD NEW CATEGORY]\n"+ "0.[EXIT]");
            ind = scannerInt.nextInt();

            if(ind == categoryList.size() + 1){
                System.out.print("Enter category name: ");
                Category category =  new Category(scannerStr.nextLine(), parentId);
                categoryService.add(category);
            }
            else if (ind > 0 && ind <= categoryList.size()){
                parentId = categoryList.get(ind - 1).getId();
                categoryService.get(parentId);
            }
//            else if(ind == categoryList.size()+2){
//                addCategoryMenu();
//            }
            else if(ind != 0) System.out.println("Invalid option");
        }while (ind != 0);

    }
///##############################################################################
    ///Adding Category Menu

//==============================Teacher Menu=======================================================
    protected static void teacherMenu(User user){
        int stepCode;
        do {
            System.out.println("                      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++    1.[ADD NEW COURSE]\t 2.[SEE MY COURSES]\t 3.[ACCOUNT]\t 0.[EXIT]  ++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++                                                                      ++");
            System.out.println("                      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();

            switch (stepCode){
                case 1 -> {
                    //ADD NEW COURSE WITH CATEGORY
                    UUID parentId = null;
//                    UUID id = null;
                    int ind = 1;
                    do {
                        ind = 1;
                        List<Category> categoryList = categoryService.getCategoryList(parentId);
                        for (Category category: categoryList) {
                            System.out.println((ind++) + ". " + category.getName());
                        }
                        System.out.println("0.[BACK]");
                        ind = scannerInt.nextInt();

                        if (ind > 0 && ind <= categoryList.size()){

                            parentId = categoryList.get(ind - 1).getId();

                            if(categoryList.get(ind - 1).isLast()){
                                addNewCourse(user, parentId);
                                ind = 0;
                            }
                        }
                        else if(ind != 0) System.out.println("Invalid option");
                    }while (ind != 0);
                }
                case 2 -> {
                    List<Course> courses = courseService.myCourses(user.getId());
                    int option = 1;
                    for (Course course: courses) {
                        System.out.println((option ++) + ". " + course.getName());
                    }
                    System.out.println("0.[EXIT]");
                    option = scannerInt.nextInt();
//                    while(option > 0 && option <= courses.size()){
//                        for (Course course: courses) {
//                            System.out.println((option ++) + ". " + course.getName());
//                        }
//                        System.out.println("0.[EXIT]");
//                        option = scannerInt.nextInt();
//                    }
                    if(option != 0){
                        courseMenu(courses.get(option-1));
                    }


                }
                case 3->{
                    account(user);
                }
            }
        }while(stepCode != 0);
    }
        // -------------ADD NEW COURSE-----------------
    public static void addNewCourse(User user, UUID parentId){
        System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      ++             1.[ADD NEW COURSE]\t 0.[EXIT]             ++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      ++                                                       ++");
        System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("                        Choose your choice:  ");
        int option = scannerInt.nextInt();

        if(option == 1){
            System.out.print("Enter course name: ");
            String courseName = scannerStr.nextLine();
            System.out.print("Enter course price: ");
            double price = scannerInt.nextDouble();
            System.out.print("Enter course discount(0 - 100): ");
            double discount = scannerInt.nextDouble();
            courseService.add(new Course(courseName, user.getId(), parentId, price, discount, user.getName(), user.getPhoneNumber()));
            System.out.println("Successfully completed");
        }

    }




    //---------------------------- Student menu------------------------------
    public static void studentMenu(User user){
        //===================================================================
        System.out.println("-------------------------------------------------");
        int stepCode;
        do {
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      ++        1.[SEE ALL COURSES]\t 2.[SEE ENROLLED COURSES]\t 3.[ACCOUNT]\t 4.[CART]\t 0.[EXIT]     ++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      ++                                                                                               ++");
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1 ->{
                    UUID parentId = null;
//                    UUID id = null;

                    int ind = 1;
                    do {
                        ind = 1;
                        List<Category> categoryList = categoryService.getCategoryList(parentId);
                        for (Category category: categoryList) {
                            System.out.println((ind++) + ". " + category.getName());
                        }
                        System.out.println("0.[BACK]");
                        ind = scannerInt.nextInt();

                        if (ind > 0 && ind <= categoryList.size()){

                            parentId = categoryList.get(ind - 1).getId();

                            if(categoryList.get(ind - 1).isLast()){
                                List<Course> courses1 = courseService.courses(parentId);
                                buyCourse(courses1, user);
                                ind = 0;
                            }
                        }
                        else if(ind != 0) System.out.println("Invalid option");
                    }while (ind != 0);

                }
                case 2->{
                    int ind = 1;
                    List<Course> myCourses = StudentCourseService.getCourses(user);
                    for(Course course: myCourses){
                        System.out.println("------------" + ind++ +"---------------");
                        System.out.println("Course name: "+course.getName());
                        System.out.println("Course price: "+course.getPrice());
                        System.out.println("Teacher name: "+course.getTeacherName());
                    }
                }
                case 3->{
                    account(user);
                }
            }
        }while(stepCode != 0);

    }

        //================================BUY Course category =========================================

        public static void buyCourse(List<Course> courses, User user){

            int ind = 1;
            Course course1;
            for (Course course: courses) {
                System.out.println("----------------------" + (ind++) + "-----------------------");
                System.out.println("Name: " + course.getName());
                System.out.println("Price: " + course.getPrice());
                System.out.println("Discount: " + course.getDiscount());
                System.out.println("Teacher: " + course.getTeacherName());
                System.out.println("-------------------------------------------------------------");
            }
            ind = scannerInt.nextInt();
            if(ind > 0 && ind <= courses.size()){
                course1 = courses.get(ind - 1);
                System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      ++    1.[BUY]\t 2.[ADD TO CART]\t 3.[BACK]\t 0.[EXIT]    ++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      ++                                                       ++");
                System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.print("                        Choose your choice:  ");
                ind = scannerInt.nextInt();
                if(ind == 1) buyMenu(course1, user);
                else if(ind == 3) buyCourse(courses, user);
            }

        }

    //================================BUY Course  MENU=========================================
    public static void buyMenu(Course course, User user){
        List<Card> cardList = cardService.getUserCards(user);

        for (int i = 0; i < cardList.size(); i++) {
            System.out.println("---------------------" + (i + 1) + "------------------------");
            System.out.println("Name: " + cardList.get(i).getName());
            System.out.println("Balance: " + cardList.get(i).getBalance());
            System.out.println("------------------------------------------------------------------\n");
        }

        int option = scannerInt.nextInt();
        if(option > 0 && option <=  cardList.size()){
            Card card = cardList.get(option - 1);
            double amount =  course.getPrice() - (course.getPrice() * course.getDiscount()/100);
            course.setNumberOfEnrolledStudents(course.getNumberOfEnrolledStudents()+1);
            StudentCourseService.add(new StudentCourse(user, course));
            card.setBalance(card.getBalance() - amount);
            owner.setBalance(owner.getBalance() + amount * owner.getCommission());
            cardService.teacherCardUpdate(course.getTeacherId(), amount - amount * owner.getCommission());
            System.out.println("successfully completed");
        }
    }

        //===================== --------COURSE-MENU--------==========================
    protected static void courseMenu(Course course){
        System.out.println("==============COURSE-INFO====================");
        System.out.println("Name: "+course.getName());
        System.out.println("Course price: "+course.getPrice());
        System.out.println("Discount: "+course.getDiscount());
        System.out.println("Enrolled Students: "+course.getNumberOfEnrolledStudents());
        System.out.println("----------------------------------------\n");

        int stepCode = 1;
        while (stepCode!=0){
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      ++    1.Change Course info\t 2.Delete Course\t 0.Exit    ++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      ++                                                       ++");
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1->{
                    changeCourseInfo(course);
                }
                case 2->{
                    course.setActive(false);
                    System.out.println("completed successfully");
                }
            }
        }
    }

                //------CHANGE COURSE INFO----------
    protected static void changeCourseInfo(Course course){
        int stepCode = 1;
        while (stepCode != 0){
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++        1.[Change course name]\t 2.[Change course price]\t 3.[Change course discount]\t 0.[Exit]       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1->{
                    System.out.println("Enter new name");
                    course.setName(scannerStr.nextLine());
                    System.out.println("completed successfully");
                }
                case 2->{
                    System.out.println("Enter new price");
                    course.setPrice(scannerInt.nextDouble());
                    System.out.println("completed successfully");
                }
                case 3->{
                    System.out.println("Enter new discount");
                    course.setDiscount(scannerInt.nextDouble());
                    System.out.println("completed successfully");
                }
            }
        }
    }
    //============================COURSE-MENU===================================

    //============================ADD-NEW-COURSE================================

    //===========================ADD-NEW-COURSE=================================


    //==========================TEACHER-ACCOUNT-PRIVACY-SETTINGS==================
    protected static void account(User user){
        System.out.println("Name: "+user.getName());
        System.out.println("PhoneNumber: "+user.getPhoneNumber());
        System.out.println("Password: "+user.getPassword());
        int stepCode = 1;
        while (stepCode!=0){
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++               1.[CHANGE NAME]\t 2.[CHANGE PHONE NUMBER]\t 3[CHANGE PASSWORD]\t 0.[EXIT]               ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      ++                                                                                                       ++");
            System.out.println("                      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                        Choose your choice:  ");
            stepCode = scannerInt.nextInt();
            switch (stepCode){
                case 1->{
                    System.out.println("Enter new name");
                    user.setName(scannerStr.nextLine());
                    System.out.println("completed successfully");
                }
                case 2->{
                    System.out.println("Enter new phone number");
                    user.setPhoneNumber(scannerStr.nextLine());
                    System.out.println("completed successfully");
                }
                case 3->{
                    System.out.println("Enter new password");
                    user.setPassword(scannerStr.nextLine());
                    System.out.println("completed successfully");
                }
            }
        }
    }


//===================================TEACHER===========================================================================

//===================================STUDENT MENU====================================================================





}
