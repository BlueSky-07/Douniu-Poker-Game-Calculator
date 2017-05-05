
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Poker {
    private String[] pokers;
    private HashMap<String, Integer> count;
    private int sum;
    private int value;

    Poker() {
        pokers = new String[5];
        count = new HashMap<>();
        sum = 0;
    }

    void gameStart() {
        if (gameIn()) {
            judge();
        }
    }

    private boolean gameIn() {
        System.out.println("what pokers ?");
        System.out.println("(\"0\" for 10, joker not allowed)");
        Scanner consoleIn = new Scanner(System.in);
        String in = consoleIn.nextLine();
        // change to upper case
        in = in.toUpperCase();
        // delete wrong characters
        in = in.replaceAll("[^\\dajqkAJQK]", "");
        // less than 5 is not right
        if (in.length() < 5) {
            System.out.println("not enough pokers");
            return false;
        }
        // take the first 5
        in = in.substring(0, 5);
        // change to array
        char[] rawPokers = in.toCharArray();
        // change 1 to A, change 0 to 10
        for (int i = 0; i < 5; i++) {
            if (rawPokers[i] == '1') {
                pokers[i] = "A";
            } else if (rawPokers[i] == '0') {
                pokers[i] = "10";
            } else {
                pokers[i] = String.valueOf(rawPokers[i]);
            }
        }
        // get count of each poker
        for (String item : pokers) {
            if (count.containsKey(item)) {
                count.put(item, count.get(item) + 1);
            } else {
                count.put(item, 1);
            }
        }
        // get sum of pokers
        for (String item : pokers) {
            sum += getPokerVal(item);
        }
//        {
//            // for debug
//            System.out.println(rawPokers);
//            System.out.println(sum);
//            System.out.println(count);
//            System.exit(0);
//        }
        return true;
    }

    private void judge() {
        if (!judgeBigBonus()) {
            if (!judgeOtherBonus()) {
                System.out.println("LOSE");
            }
        }
    }

    private boolean judgeBigBonus() {
        // bomb
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 4) {
                System.out.printf("BOMB : %s\n", entry.getKey());
                return true;
            }
        }
        // five bulls
        if (sum == 50) {
            System.out.println("FIVE BULLS");
            return true;
        }
        // five small bulls
        if (sum == 10) {
            System.out.println("FIVE SMALL BULLS");
            return true;
        }
        return false;
    }

    private boolean judgeOtherBonus() {
        value = sum % 10 == 0 ? 10 : sum % 10;
        if (judge10(0, 1, 2)) {
            showBull(0, 1, 2);
        } else if (judge10(0, 1, 3)) {
            showBull(0, 1, 3);
        } else if (judge10(0, 1, 4)) {
            showBull(0, 1, 4);
        } else if (judge10(0, 2, 3)) {
            showBull(0, 2, 3);
        } else if (judge10(0, 2, 4)) {
            showBull(0, 2, 4);
        } else if (judge10(0, 3, 4)) {
            showBull(0, 3, 4);
        } else if (judge10(1, 2, 3)) {
            showBull(1, 2, 3);
        } else if (judge10(1, 2, 4)) {
            showBull(1, 2, 4);
        } else if (judge10(1, 3, 4)) {
            showBull(1, 3, 4);
        } else if (judge10(2, 3, 4)) {
            showBull(2, 3, 4);
        } else {
        	return false;
        }
        return true;
    }

    private int getPokerVal(String poker) {
        switch (poker) {
            case "A":
                return 1;
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.valueOf(poker);
        }
    }

    private boolean judge10(int a, int b, int c) {
        return (getPokerVal(pokers[a]) + getPokerVal(pokers[b]) + getPokerVal(pokers[c])) % 10 == 0;
    }

    private void showBull(int a, int b, int c) {
        System.out.printf("BULLS %d :\n[ ", value);
        int d = -1, e = -1;
        for (int i = 0; i < 5; i++) {
            if (i == a || i == b || i == c) {
                System.out.printf("%s ", pokers[i]);
            } else if (d == -1) {
                d = i;
            } else {
                e = i;
            }
        }
        System.out.printf("] + ( %s %s )\n", pokers[d], pokers[e]);
    }
}
