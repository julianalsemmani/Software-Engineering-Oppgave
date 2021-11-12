package persist;

import core.model.Product;
import core.model.Store;
import core.model.User;
import core.repository.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import java.util.function.Function;

public class HibernateRepository implements Repository {

    private final SessionFactory sessionFactory;

    public HibernateRepository(SessionFactory sessionFactory) {
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
    public Set<User> getAllEmployees(UUID storeId) {
//        return getStoreById(storeId).employees;
        return null;
    }

    @Override
    public List<Product> getAllProducts(UUID storeId) {
        return null;
    }

    @Override
    public Product getProductById(UUID productId) {
        return callSession(session -> session.get(Product.class, productId));
    }

    @Override
    public Store getStoreById(UUID storeId) {
        return callSession(session -> session.get(Store.class, storeId));
    }

    @Override
    public Store createStore(String storeName, User owner, String address, int phoneNumber) {
        Store newStore = new Store(UUID.randomUUID(), storeName, owner, new HashSet<>(), address, phoneNumber, new HashSet<>());
        doUnitOfWork(session -> session.save(newStore));
        return newStore;
    }

    @Override
    public User getUserById(UUID userId) {
        return callSession(session -> session.get(User.class, userId));
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
        User newUser = new User(0, username, password, firstName, lastName, address, email, 0, false);
        doUnitOfWork(session -> session.save(newUser));
        return newUser;
    }

    @Override
    public User updateUser(UUID userId, String username, String password, String firstName, String lastName, String address, String email) {
        return doUnitOfWork(session -> {
            User user = session.get(User.class, userId);

            if (user != null) {
                if (username != null) user.username = username;
                if (password != null) user.password = password;
                if (firstName != null) user.firstName = firstName;
                if (lastName != null) user.lastName = lastName;
                if (address != null) user.address = address;
                if (email != null) user.email = email;

                session.update(user);
            }

            return user;
        });
    }

    @Override
    public User deleteUser(UUID userId) {
        return doUnitOfWork(session -> {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
            return user;
        });
    }

    @Override
    public Product createProduct(UUID storeId, String name, String productPicture) {
        return doUnitOfWork(session -> {
            Store store = session.get(Store.class, storeId);

            Product product = new Product(UUID.randomUUID(), store, name, productPicture);
            session.save(product);
            store.addProduct(product);
            session.save(store);
            return product;
        });
    }

    @Override
    public Product updateProduct(UUID productId, String name, String productPicture) {
        return doUnitOfWork(session -> {
            Product product = session.get(Product.class, productId);
            if (product != null) {
                if (name != null) product.name = name;
                if (productPicture != null) product.productPicture = productPicture;
            }
            session.save(product);

            return product;
        });
    }

    @Override
    public Product deleteProduct(UUID productId) {
        return doUnitOfWork(session -> {
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
            }
            return product;
        });
    }

    @Override
    public void registerEmployee(UUID storeId, UUID newEmployeeId) {
        doUnitOfWork(session -> {
            Store store = session.get(Store.class, storeId);
            User newEmployee = session.get(User.class, newEmployeeId);
            if (store != null && newEmployee != null) {
                store.employees.add(newEmployee);
                session.save(store);
            }
            return null;
        });
    }
}
