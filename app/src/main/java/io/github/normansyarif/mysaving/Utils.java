package io.github.normansyarif.mysaving;

class Utils {
    static String numberToMoney(int number) {
        return "Rp. " + String.format("%,d", number);
    }

    static int moneyToNumber(String money) {
        return Integer.parseInt(money.replace("Rp. ", "").replace(",", "").replace(".", ""));
    }
}
