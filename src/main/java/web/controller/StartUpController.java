package web.controller;

import core.repository.Repository;

public class StartUpController {
    public final Repository repository;

    public StartUpController(Repository repository) {
        this.repository = repository;
    }
}
