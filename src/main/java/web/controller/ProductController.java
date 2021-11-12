package web.controller;

import core.repository.Repository;
import io.javalin.http.Context;

public class ProductController {
    public final Repository repository;

    public ProductController(Repository repository) {
        this.repository = repository;
    }

}
