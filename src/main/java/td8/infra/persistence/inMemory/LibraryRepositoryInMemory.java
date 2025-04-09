package td8.infra.persistence.inMemory;

import td8.domain.Library;
import td8.domain.LibraryName;
import td8.domain.LibraryRepository;

import java.util.HashMap;
import java.util.Map;

public class LibraryRepositoryInMemory implements LibraryRepository {

    private static final Map<LibraryName, Library> libraries = new HashMap<>();

    @Override
    public Library findByName(LibraryName name) {
        return libraries.get(name);
    }

    @Override
    public void saveOrUpdate(Library library) {
        libraries.put(library.getName(), library);
    }
}
