import java.util.HashMap;
import java.util.Map;

class Model {
    private String[] pokers;
    private HashMap<String, Integer> countMap;
    private int sum;
    private int value;
    private StringBuilder judgeText;
    private boolean valid;

    Model() {
        pokers = new String[5];
        countMap = new HashMap<>();
        sum = 0;
        judgeText = new StringBuilder();
        valid = false;
    }

    void init(String str) throws NotEnoughError {
        // change to upper case
        str = str.toUpperCase();
        // delete wrong characters
        str = str.replaceAll("[^\\dajqkAJQK]", "");
        // less than 5 is not right
        if (str.length() < 5) {
            throw new NotEnoughError();
        }
        // take the first 5
        str = str.substring(0, 5);
        // change to array
        char[] rawPokers = str.toCharArray();
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
            if (countMap.containsKey(item)) {
                countMap.put(item, countMap.get(item) + 1);
            } else {
                countMap.put(item, 1);
            }
        }
        // get sum of pokers
        for (String item : pokers) {
            sum += getPokerVal(item);
        }
        valid = true;
    }

    StringBuilder judge() {
        if (!judgeBigBonus()) {
            if (!judgeOtherBonus()) {
                judgeText.append("LOSE\n");
            }
        }
        return judgeText;
    }

    boolean isValid() {
        return valid;
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

    private boolean judgeBigBonus() {
        // bomb
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 4) {
                judgeText.append("BOMB : ");
                judgeText.append(entry.getKey());
                judgeText.append("\n");
                return true;
            }
        }
        // five bulls
        if (sum == 50) {
            judgeText.append("FIVE BULLS\n");
            return true;
        }
        // five small bulls
        if (sum == 10) {
            judgeText.append("FIVE SMALL BULLS\n");
            return true;
        }
        return false;
    }

    private boolean judgeOtherBonus() {
        value = sum % 10 == 0 ? 10 : sum % 10;
        if (judge10(0, 1, 2)) {
            writeJudgeText(0, 1, 2);
        } else if (judge10(0, 1, 3)) {
            writeJudgeText(0, 1, 3);
        } else if (judge10(0, 1, 4)) {
            writeJudgeText(0, 1, 4);
        } else if (judge10(0, 2, 3)) {
            writeJudgeText(0, 2, 3);
        } else if (judge10(0, 2, 4)) {
            writeJudgeText(0, 2, 4);
        } else if (judge10(0, 3, 4)) {
            writeJudgeText(0, 3, 4);
        } else if (judge10(1, 2, 3)) {
            writeJudgeText(1, 2, 3);
        } else if (judge10(1, 2, 4)) {
            writeJudgeText(1, 2, 4);
        } else if (judge10(1, 3, 4)) {
            writeJudgeText(1, 3, 4);
        } else if (judge10(2, 3, 4)) {
            writeJudgeText(2, 3, 4);
        } else {
            return false;
        }
        return true;
    }

    private void writeJudgeText(int a, int b, int c) {
        judgeText.append("BULLS ");
        judgeText.append(value);
        judgeText.append(" :\n[ ");
        int d = -1, e = -1;
        for (int i = 0; i < 5; i++) {
            if (i == a || i == b || i == c) {
                judgeText.append(pokers[i]);
                judgeText.append(" ");
            } else if (d == -1) {
                d = i;
            } else {
                e = i;
            }
        }
        judgeText.append("] + ( ");
        judgeText.append(pokers[d]);
        judgeText.append(" ");
        judgeText.append(pokers[e]);
        judgeText.append(" )\n");
    }
}

class NotEnoughError extends Error {
    NotEnoughError() {
        super("not enough pokers");
    }
}