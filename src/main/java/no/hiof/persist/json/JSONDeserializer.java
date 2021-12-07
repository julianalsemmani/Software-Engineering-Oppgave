package no.hiof.persist.json;

import no.hiof.core.model.Store;
import no.hiof.core.model.User;

import java.util.Map;
import java.util.UUID;

public interface JSONDeserializer<T> {
    T deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap);
}
