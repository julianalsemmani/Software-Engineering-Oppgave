package persist;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.repository.StartUpRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateStartUpRepository implements StartUpRepository {

    private final SessionFactory sessionFactory;

    public HibernateStartUpRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private <T> T callSession(Function<Session, T> callback) {
        Session session = sessionFactory.openSession();
        T result = callback.apply(session);
        session.close();
        return result;
    }

    private <T> T doUnitOfWork(Function<Session, T> unitOfWork) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T result = unitOfWork.apply(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Store> getAllStores() {
        return null;
    }

    @Override
    public List<User> getAllEmployees(int storeId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(int storeId) {
        return null;
    }

    @Override
    public Product getAProduct(int storeId, int productId) {
        return null;
    }

    @Override
    public Store getStoreById(int storeId) {
        return null;
    }

    @Override
    public Store createStore(String storeName, User owner, String address, int phoneNumber) {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return callSession(session -> session.get(User.class, userId));
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
        User newUser = new User(0, username, password, firstName, lastName, address, email, 0, false);
        doUnitOfWork(session -> session.save(newUser));
        return newUser;
    }

    @Override
    public User updateUser(int userId, String username, String password, String firstName, String lastName, String address, String email) {
        return doUnitOfWork(session -> {
            User user = session.get(User.class, userId);

            if(user != null) {
                if(username != null) user.username = username;
                if(password != null) user.password = password;
                if(firstName != null) user.firstName = firstName;
                if(lastName != null) user.lastName = lastName;
                if(address != null) user.address = address;
                if(email != null) user.email = email;

                session.update(user);
            }

            return user;
        });
    }

    @Override
    public User deleteUser(int userId) {
        return doUnitOfWork(session -> {
            User user = session.get(User.class, userId);
            if(user != null) {
                session.delete(user);
            }
            return user;
        });
    }

    @Override
    public Product createProduct(int storeId, String name, String productPicture) {
        return null;
    }

    @Override
    public Product updateProduct(int storeId, int productId, String name, String productPicture) {
        return null;
    }

    @Override
    public Product deleteProduct(int storeId, int productId) {
        return null;
    }

    @Override
    public void registerEmployee(int storeId, int newEmployeeId) {

    }
}
