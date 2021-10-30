package core.controller;

import core.repository.StartUpRepository;

public class ProductController {
    public final StartUpRepository startUpRepository;

    public ProductController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }
}
