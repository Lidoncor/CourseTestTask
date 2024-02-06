package stats;

import configuration.FileInfo;

public class StringStats {

    private final FileInfo type;

    private long quantity = 0;

    private long minLength = Long.MAX_VALUE;

    private long maxLength = Long.MIN_VALUE;

    public StringStats(FileInfo type) {
        this.type = type;
    }

    public void add(String newVal) {
        quantity += 1;
        minLength = Math.min(newVal.length(), minLength);
        maxLength = Math.max(newVal.length(), maxLength);
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
                        ", minLength=" + minLength +
                        ", maxLength=" + maxLength
        );
    }
}
