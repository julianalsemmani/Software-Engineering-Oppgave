package core.fakes;

import core.dtos.StoreUserDTO;
import core.model.StoreUser;
import core.repository.StartUpRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeStartUpRepository implements StartUpRepository {
    List<StoreUser> storeUserList = new ArrayList<>();

    @Override
    public void createStoreUser(StoreUserDTO dto) {
        storeUserList.add(new StoreUser(dto.firstName, dto.lastName, dto.address, dto.email, dto.userName, dto.password));
    }

    public void dumpFakeData() {
        storeUserList.clear();
    }
}
