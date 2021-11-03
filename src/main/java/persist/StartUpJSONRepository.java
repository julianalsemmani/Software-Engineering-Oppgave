package persist;

import core.dtos.StoreUserDTO;
import core.repository.StartUpRepository;

public class StartUpJSONRepository implements StartUpRepository {
    public StartUpJSONRepository(String fileName) {
    }

    @Override
    public void createStoreUser(StoreUserDTO dto) {

    }
}
