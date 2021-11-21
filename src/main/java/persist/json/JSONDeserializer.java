package persist.json;

import core.model.Store;
import core.model.User;

import java.util.Map;
import java.util.UUID;

public interface JSONDeserializer<T> {
    T deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap);
}
