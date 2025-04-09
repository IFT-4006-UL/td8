package td8;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import td8.api.ConfigurationServerRest;
import td8.api.filters.CORSFilter;
import td8.domain.Library;
import td8.domain.LibraryName;
import td8.domain.LibraryRepository;

import java.util.HashMap;

public class Main implements Runnable {
    public static final String TestEnvironmentArg = "TEST";
    public static final int PORT = 8181;
    public static final String DEFAULT_LIBRARY_NAME = "Ulaval";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static Server server;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals(TestEnvironmentArg)) {
            logger.info("Running with test environment");
            ConfigurationServerRest.useInMemoryRepositories();
        }

        try {
            new Main().run();
            server.join();
        } catch (InterruptedException e) {
            logger.error("Error starting server", e);
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }

    public static void runForTest() {
        new Main().run();
    }

    public void run() {
        server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = new ResourceConfig()
                .packages("td8")
                .register(JacksonFeature.withoutExceptionMappers())
                .register(ConfigurationServerRest.class)
                .register(CORSFilter.class);
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            logger.info("Server started on port " + PORT);

            LibraryRepository libraryRepository = getLibraryRepositoryFromContainer(container);
            initializeLibraryRepositoryWithDefaultLibrary(libraryRepository);

        } catch (Exception e) {
            logger.error("Error starting server", e);
        }
    }

    public static void stop() throws Exception {
        logger.info("Shutting down the application...");
        server.stop();
    }

    private LibraryRepository getLibraryRepositoryFromContainer(ServletContainer container) {
        return container.getApplicationHandler().getInjectionManager().getInstance(LibraryRepository.class);
    }

    private void initializeLibraryRepositoryWithDefaultLibrary(LibraryRepository libraryRepository) {
        Library library = new Library(new LibraryName(DEFAULT_LIBRARY_NAME),
                                      new HashMap<>());

        libraryRepository.saveOrUpdate(library);
    }
}