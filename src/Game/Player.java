package Game;

import java.io.Serializable;

public class Player implements Serializable {
    private final String name;
    private int withdrawQuota;

    public Player() {
        name = Utils.getRandomString(5);
        withdrawQuota = 3;
    }

    public int getWithdrawQuota() {
        return withdrawQuota;
    }

    public Player(String _name, int _numOfRemainPieces) {
        name = _name;
    }

    public boolean checkWithdrawQuota() {
        return withdrawQuota >= 0;
    }

    public void deductWithdrawQuota() {
        withdrawQuota--;
    }

    public String getName() {
        return name;
    }
}
