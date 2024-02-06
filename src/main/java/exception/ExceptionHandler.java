package exception;

import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e.getClass().equals(ParameterException.class)) {
            System.out.println("Не указаны файлы для обработки");
        } else if (e.getClass().equals(IOException.class)) {
            System.out.println(e.getMessage());
        } else if (e.getClass().equals(NoSuchFileException.class)) {
            System.out.println("Такого файла не существует: " + e.getMessage());
        } else if (e.getClass().equals(AccessDeniedException.class)) {
            System.out.println("Нет прав доступа к файлу: " + e.getMessage());
        }
    }
}
