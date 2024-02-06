package configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileInfo {
    INTEGERS("integers.txt"),
    FLOATS("floats.txt"),
    STRINGS("strings.txt");

    private final String name;
}
