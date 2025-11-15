import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> tracableParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(10);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(10);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(10);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportTracking();
                    break;
                case 5:
                    showBoxContents();
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить статус доставки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void printParcelTypes() {
        System.out.println("1 - обычная посылка");
        System.out.println("2 - хрупкая посылка");
        System.out.println("3 - скоропортящаяся посылка");
    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите описание посылки");
        String description = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введите вес посылки");
        int weight = scanner.nextInt();
        System.out.println("Введите адрес доставки");
        String deliveryAddress = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введите день отправки");
        int sendDay = scanner.nextInt();
        System.out.println("Введите тип посылки");
        printParcelTypes();
        int ParcelType = scanner.nextInt();
        switch (ParcelType) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                tracableParcels.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок годности посылки");
                int timeToLive = scanner.nextInt();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный выбор.");

        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }
    // Пройти по allParcels, вызвать packageItem() и deliver()


    private static void calculateCosts() {
        int total = 0;
        for (Parcel parcel : allParcels) {
            total += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок " + total);// Посчитать общую стоимость всех доставок и вывести на экран
    }

    private static void reportTracking() {
        Scanner scanner = new Scanner(System.in);
        for (Trackable parcel : tracableParcels) {
            FragileParcel fragileParcel = (FragileParcel) parcel;
            System.out.println("Введите новый адрес для посылки " + fragileParcel.getDescription());
            String newLocation = scanner.nextLine();
            fragileParcel.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Содержимое какое коробки вы хотите посмотреть?");
        printParcelTypes();
        int choise = scanner.nextInt();
        switch (choise) {
            case 1:
                standardBox.getAllParcels();
                break;
            case 2:
                fragileBox.getAllParcels();
                break;
            case 3:
                perishableBox.getAllParcels();
                break;
            default:
                System.out.println("неверный выбор");
        }

    }
}



