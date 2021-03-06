package no.hiof.persist.json;

import no.hiof.core.model.Identified;

import java.util.Collection;
import java.util.UUID;

public class JSONUtils {
    public static UUID[] mapToIds(Collection<? extends Identified> identities) {
        return identities.stream().map(Identified::getId).toArray(UUID[]::new);
    }
}
