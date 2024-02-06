package configuration;

import com.beust.jcommander.Parameter;
import lombok.Getter;

import java.util.List;

@Getter
public class Parameters {

    @Parameter(required = true, description = "Файлы для обработки")
    private List<String> filePaths;

    @Parameter(names = "-o", description = "Путь для результатов")
    private String path = "";

    @Parameter(names = "-p", description = "Префикс имен выходных файлов")
    private String prefix = "";

    @Parameter(names = "-a", description = "Режим добавления в существующие файлы")
    private boolean append = false;

    @Parameter(names = "-s", description = "Краткая статистика")
    private boolean shortStats = false;

    @Parameter(names = "-f", description = "Полная статистика")
    private boolean fullStats = false;

}
