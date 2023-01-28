package dk.shadow.utils;



import static dk.shadow.Events.econ;

public class Econ {

    public boolean addMoney(String player, double amount) {

        return econ.depositPlayer(player, amount).transactionSuccess();
    }

    private boolean addMoneyToPlayer(String playerName, double amount) {
        return econ.depositPlayer(playerName, amount).transactionSuccess();
    }
    private boolean createPlayerAccount(String playerName) {
        return econ.createPlayerAccount(playerName);
    }

    private double getbalance(String playerName) {
        return econ.getBalance(playerName);
    }

}
