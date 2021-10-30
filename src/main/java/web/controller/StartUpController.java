package web.controller;

import core.repository.StartUpRepository;

public class StartUpController {
    public final StartUpRepository startUpRepository;

    public StartUpController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }
}
