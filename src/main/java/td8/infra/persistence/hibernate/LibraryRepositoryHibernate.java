package td8.infra.persistence.hibernate;

import td8.domain.Library;
import td8.domain.LibraryName;
import td8.domain.LibraryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryRepositoryHibernate implements LibraryRepository {
    private static final Logger logger = LoggerFactory.getLogger(LibraryRepositoryHibernate.class);
    @Override
    public Library findByName(LibraryName name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Library.class, name);
        } catch (Exception e) {
            logger.error("Error while trying to find Library with name " + name, e);
            throw e;
        }
    }

    @Override
    public void saveOrUpdate(Library library) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(library);

            transaction.commit();
        } catch (Exception e) {
            logger.error("Error while trying to save Library with name " + library.getName(), e);

            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }

            throw e;
        }
    }
}
