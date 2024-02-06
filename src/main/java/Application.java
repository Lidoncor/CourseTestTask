import com.beust.jcommander.JCommander;
import configuration.FileInfo;
import configuration.Parameters;
import exception.ExceptionHandler;
import stats.FloatStats;
import stats.IntStats;
import stats.StringStats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws IOException {
        var exceptionHandler = new ExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        Parameters parameters = new Parameters();
        JCommander.newBuilder().addObject(parameters).build().parse(args);

        var integersToWrite = new ArrayList<String>();
        var floatsToWrite = new ArrayList<String>();
        var stringsToWrite = new ArrayList<String>();

        var intStats = new IntStats(FileInfo.INTEGERS);
        var floatStats = new FloatStats(FileInfo.FLOATS);
        var stringStats = new StringStats(FileInfo.STRINGS);

        var files = new ArrayList<ArrayDeque<String>>();
        for (var path : parameters.getFilePaths()) {
            try (var fileContent = Files.lines(Paths.get(path))) {
                files.add(new ArrayDeque<>(fileContent.toList()));
            }
        }

        var iterator = files.iterator();
        int readedFiles = 0;
        while (true) {
            if (iterator.hasNext()) {
                var file = iterator.next();

                if (file.isEmpty()) {
                    readedFiles++;
                    continue;
                }

                var line = file.pop();

                var intRes = tryToInt(line);
                if (intRes.isPresent()) {
                    intStats.add(intRes.get());
                    integersToWrite.add(intRes.get().toString());
                    continue;
                }

                var floatRes = tryToFloat(line);
                if (floatRes.isPresent()) {
                    floatStats.add(floatRes.get());
                    floatsToWrite.add(floatRes.get().toString());
                    continue;
                }

                stringStats.add(line);
                stringsToWrite.add(line);
            } else {
                if (readedFiles == files.size()) {
                    break;
                }
                iterator = files.iterator();
                readedFiles = 0;
            }
        }

        tryToWrite(integersToWrite, parameters.isAppend(), FileInfo.INTEGERS, parameters.getPath(), parameters.getPrefix());
        tryToWrite(floatsToWrite, parameters.isAppend(), FileInfo.FLOATS, parameters.getPath(), parameters.getPrefix());
        tryToWrite(stringsToWrite, parameters.isAppend(), FileInfo.STRINGS, parameters.getPath(), parameters.getPrefix());

        if (parameters.isFullStats()) {
            intStats.printFullStats();
            floatStats.printFullStats();
            stringStats.printFullStats();
        } else if (parameters.isShortStats()) {
            intStats.printShortStats();
            floatStats.printShortStats();
            stringStats.printShortStats();
        }
    }

    private static Optional<Long> tryToInt(String str) {
        Long result = null;
        try {
            result = Long.parseLong(str);
        } catch (Exception ignored) {}
        return Optional.ofNullable(result);
    }

    private static Optional<Double> tryToFloat(String str) {
        Double result = null;
        try {
            result = Double.parseDouble(str);
        } catch (Exception ignored) {}
        return Optional.ofNullable(result);
    }

    private static void tryToWrite(ArrayList<String> toWrite, Boolean isAppend, FileInfo fileInfo,
                                   String userPath, String userPrefix) throws IOException {
        if (toWrite.isEmpty()) {
            return;
        }

        var option = isAppend ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;

        var path = !userPath.isEmpty() ? userPath + "/" : "";
        var fullPath = Paths.get(path + userPrefix + fileInfo.getName());

        Files.write(fullPath, toWrite, StandardOpenOption.CREATE, option);
    }
}