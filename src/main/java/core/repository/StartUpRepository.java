package core.repository;

import core.dtos.StoreUserDTO;

public interface StartUpRepository {
    void createStoreUser(StoreUserDTO dto);
}
