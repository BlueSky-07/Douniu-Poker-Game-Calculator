import java.util.Scanner;

class Controller {
    private Model game;

    void next() {
        game = new Model();
        Scanner in = new Scanner(System.in);
        while (!game.isValid()) {
            try {
                game.init(in.nextLine());
            } catch (NotEnoughError error) {
                System.err.println(error.getMessage());
            }
        }
    }

    String judge() {
        return game.judge().toString();
    }
}
