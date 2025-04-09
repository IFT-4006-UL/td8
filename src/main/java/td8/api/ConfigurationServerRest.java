package td8.api;

import td8.application.BookAssembler;
import td8.application.LibraryService;
import td8.domain.LibraryRepository;
import td8.infra.persistence.hibernate.LibraryRepositoryHibernate;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import td8.infra.persistence.inMemory.LibraryRepositoryInMemory;

public class ConfigurationServerRest extends AbstractBinder {
    private static LibraryRepository LibraryRepositoryInstance = new LibraryRepositoryHibernate();

    public static void useInMemoryRepositories() {
        LibraryRepositoryInstance = new LibraryRepositoryInMemory();
    }

    @Override
    protected void configure() {
        bindAsContract(BookAssembler.class);
        bindAsContract(LibraryService.class);
        bind(LibraryRepositoryInstance).to(LibraryRepository.class);
    }
}
