package stats;

import configuration.FileInfo;

public class FloatStats {

    private final FileInfo type;

    private long quantity = 0;

    private double min = Double.MAX_VALUE;

    private double max = Double.MIN_VALUE;

    private double sum = 0;

    private double avg = 0;

    public FloatStats(FileInfo type) {
        this.type = type;
    }

    public void add(double newVal) {
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
