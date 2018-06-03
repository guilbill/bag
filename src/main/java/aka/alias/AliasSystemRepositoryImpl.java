package aka.alias;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class AliasSystemRepositoryImpl implements AliasSystemRepository {

    @Override
    public Set<Alias> getAliases() throws IOException {
        Process process = new ProcessBuilder("bash", "-i", "src/main/resources/alias.sh").start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        return reader.lines()
                .map(Alias::fromString)
                .collect(toSet());
    }

}