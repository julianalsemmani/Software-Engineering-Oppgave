package core.service;

import core.model.*;
import core.repository.Repository;

import java.time.Instant;
import java.util.*;

public class Service {
    public final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public User createUser(String username, String password, String firstName, String lastName, String address, String email) {
        User newUser = new User(UUID.randomUUID(), username, password, firstName, lastName, address, email, 0, false);

        repository.addUser(newUser);

        return newUser;
    }

    public User updateUser(UUID userId, String username, String password, String firstName, String lastName, String address, String email) {
        User user = repository.getUserById(userId);

        if(user != null) {
            if(username != null) user.username = username;
            if(password != null) user.password = password;
            if(firstName != null) user.firstName = firstName;
            if(lastName != null) user.lastName = lastName;
            if(address != null) user.address = address;
            if(email != null) user.email = email;

            repository.updateUser(user);
        }

        return user;
    }

    public User deleteUser(UUID userId) {
        User deletedUser = repository.getUserById(userId);
        repository.deleteUser(deletedUser);
        return deletedUser;
    }

    public Store createStore(String storeName, User owner, String address, int phoneNumber) {
        Store newStore = new Store(UUID.randomUUID(), storeName, owner, new HashSet<>(), address, phoneNumber, new HashSet<>());

        repository.addStore(newStore);

        return newStore;
    }

    public Store updateStore(UUID storeId, String storeName, User owner, String address, int phoneNumber) {
        Store store = repository.getStoreById(storeId);
        if(store != null) {
            store.storeName = storeName;
            store.owner = owner;
            store.address = address;
            store.phoneNumber = phoneNumber;

            repository.updateStore(store);
        }
        return store;
    }

    public Store deleteStore(UUID storeId) {
        Store deletedStore = repository.getStoreById(storeId);
        repository.deleteStore(deletedStore);
        return deletedStore;
    }

    Set<User> getAllEmployees(UUID storeId) {
        return repository.getStoreById(storeId).employees;
    }

    List<Product> getAllProducts(UUID storeId) {
        return repository.getStoreById(storeId).getAllProducts();
    }

    public Product getProductById(UUID storeId, UUID productId) {
        return repository.getStoreById(storeId).products.get(productId);
    }

    public Product createProduct(UUID storeId, String name, String productPicture) {
        Store store = repository.getStoreById(storeId);
        Product newProduct = new Product(UUID.randomUUID(), name, productPicture, null);
        store.addProduct(newProduct);

        repository.updateStore(store);

        return newProduct;
    }

    public Auction registerAuction(UUID storeId, UUID productId, int startBid, int minimumBidIncrement, int buyoutPrice, Instant auctionStartTime, Instant auctionEndTime) {
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);
        Auction auction = new Auction(startBid, minimumBidIncrement, buyoutPrice, auctionStartTime, auctionEndTime, new ArrayList<>());
        product.saleMethod = auction;

        repository.updateStore(store);

        return auction;
    }

    public Auction updateAuction(UUID storeId, UUID productId, int startBid, int minimumBidIncrement, int buyoutPrice, Instant auctionStartTime, Instant auctionEndTime) {
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);
        if(product.saleMethod instanceof Auction) {
            Auction auction = (Auction) product.saleMethod;
            auction.startBid = startBid;
            auction.minimumBidIncrement = minimumBidIncrement;
            auction.buyoutPrice = buyoutPrice;
            auction.auctionStartTime = auctionStartTime;
            auction.auctionEndTime = auctionEndTime;

            repository.updateStore(store);

            return auction;
        }

        return null;
    }

    public Sale registerSale(UUID storeId, UUID productId, int price) {
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);
        Sale sale = new Sale(null, price);
        product.saleMethod = sale;

        repository.updateStore(store);

        return sale;
    }

    public Sale updateSale(UUID storeId, UUID productId, int price) {
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);

        if(product.saleMethod instanceof Sale) {
            Sale sale = (Sale) product.saleMethod;

            sale.price = price;

            repository.updateStore(store);
            return sale;
        }

        return null;
    }

    public Product updateProduct(UUID storeId, UUID productId, String name, String productPicture) {
        Store store = repository.getStoreById(storeId);

        Product product = store.products.get(productId);
        if(product != null) {
            if(name != null) product.name = name;
            if(productPicture != null) product.productPicture = productPicture;

            repository.updateStore(store);

            return product;
        }

        return null;
    }

    public Product deleteProduct(UUID storeId, UUID productId) {
        Store store = repository.getStoreById(storeId);
        Product product = store.products.remove(productId);

        repository.updateStore(store);

        return product;
    }

    public void registerEmployee(UUID storeId, UUID newEmployeeId) {
        Store store = repository.getStoreById(storeId);

        User newEmployee = repository.getUserById(newEmployeeId);
        store.addEmployee(newEmployee);

        repository.updateStore(store);
    }

    public StartUp updateStartUp(String name, String address, int phoneNumber) {
        StartUp startUp = repository.getStartUp();
        if(name != null) startUp.name = name;
        if(address != null) startUp.address = address;
        if(phoneNumber != 0) startUp.phoneNumber = phoneNumber;

        repository.updateStartUp();

        return startUp;
    }

    public boolean doBid(UUID bidderId, UUID storeId, UUID productId, int price) {
        boolean success = false;

        User bidder = repository.getUserById(bidderId);
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);

        if(product.saleMethod instanceof Auction) {
            Auction auction = (Auction) product.saleMethod;
            AuctionBid bid = new AuctionBid(bidder, price, Instant.now());

            success = auction.doBid(bid);

            repository.updateStore(store);
        }

        return success;
    }

    public boolean doSale(UUID storeId, UUID productId, UUID buyerId) {
        boolean success = false;

        User buyer = repository.getUserById(buyerId);
        Store store = repository.getStoreById(storeId);
        Product product = store.products.get(productId);

        if (product.saleMethod instanceof Sale) {
            Sale sale = (Sale) product.saleMethod;

            success = sale.doSale(buyer);

            repository.updateStore(store);
        }

        return success;
    }
}
