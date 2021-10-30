package core.controller;

import core.repository.StartUpRepository;

public class StoreController {
    public final StartUpRepository startUpRepository;

    public StoreController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }
}
