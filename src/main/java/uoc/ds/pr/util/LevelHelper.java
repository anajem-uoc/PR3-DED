package uoc.ds.pr.util;

import uoc.ds.pr.enums.CollectorLevel;

public class LevelHelper {
    public static CollectorLevel getLevel(int points) {
        if (points >= 5000) {
            return CollectorLevel.DIAMOND;
        } else if (points >= 2000) {
            return CollectorLevel.PLATINUM;
        } else if (points >= 500) {
            return CollectorLevel.GOLD;
        } else if (points >= 200) {
            return CollectorLevel.SILVER;
        } else {
            return CollectorLevel.BRONZE;
        }
    }
}
