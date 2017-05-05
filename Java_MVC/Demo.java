public class Demo {
    public static void main(String[] args) {
        System.out.println("welcome to Poker game:");
        View game = new View();
        while (true) {
            game.menu();
            game.bonus();
        }
    }
}
