package stats;

import configuration.FileInfo;

public class IntStats {

    private final FileInfo type;

    private long quantity = 0;

    private long min = Long.MAX_VALUE;

    private long max = Long.MIN_VALUE;

    private long sum = 0;

    private long avg = 0;

    public IntStats(FileInfo type) {
        this.type = type;
    }

    public void add(Long newVal) {
        quantity += 1;
        min = Math.min(newVal, min);
        max = Math.max(newVal, max);
        sum += newVal;
        avg = sum / quantity;
    }

    public void printShortStats() {
        if (quantity == 0) {
            return;
        }
        System.out.println(
                "type=" + type.name().toLowerCase() +
                        ", quantity=" + quantity
        );
    }

    public void printFullStats() {
        if (quantity == 0) {
            return;
        }
        System.out.println(
                "type=" + type.name().toLowerCase() +
                        ", quantity=" + quantity +
                        ", min=" + min +
                        ", max=" + max +
                        ", avg=" + avg +
                        ", sum=" + sum
        );
    }
}
