import java.util.Scanner;

public class Race {
    private final Scanner scanner;
    private final Car[] cars;

    public Race() {
        this.scanner = new Scanner(System.in);
        this.cars = new Car[3];
    }

    public void startRace() {
        for (int i = 0; i < cars.length; i++) {
            cars[i] = createCar(i + 1);
        }

        Car winner = determineWinner();
        System.out.println("Самая быстрая машина: " + winner.getName());
    }

    private Car createCar(int carNumber) {
        System.out.println("Автомобиль #" + carNumber);

        String name = getValidName(carNumber);
        int speed = getValidSpeed(carNumber);

        return new Car(name, speed);
    }

    private String getValidName(int carNumber) {
        while (true) {
            System.out.println("Введите название автомобиля: ");
            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Ошибка: название не может быть пустым!");
        }
    }

    private int getValidSpeed(int carNumber) {
        while (true) {
            System.out.println("Введите скорость автомобиля (1-250 км/ч): ");
            String input = scanner.nextLine();

            try {
                int speed = Integer.parseInt(input);
                if (speed >= 1 && speed <= 250) {
                    return speed;
                }
                System.out.println("Ошибка: скорость должна быть от 1 до 250 км/ч!");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    private Car determineWinner() {
        Car winner = cars[0];
        int maxDistance = winner.calculateDistance();

        for (int i = 1; i < cars.length; i++) {
            int currentDistance = cars[i].calculateDistance();
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                winner = cars[i];
            }
        }

        return winner;
    }
}