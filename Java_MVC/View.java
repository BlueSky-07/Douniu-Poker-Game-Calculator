class View {
    private Controller game;

    View() {
        game = new Controller();
    }

    void menu() {
        System.out.println("what pokers?");
        System.out.println("(\"0\" for 10, joker not allowed)");
        game.next();
    }

    void bonus() {
        String judgeText = game.judge();
        System.out.print(judgeText);
    }
}
