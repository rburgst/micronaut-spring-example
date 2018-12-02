package greeting.example;

import java.util.Objects;
import java.util.UUID;

public abstract class GenericId {
    private UUID uuid;

    public GenericId(UUID uuid) {
        this.uuid = uuid;
    }

    public GenericId(String string) {
        this(UUID.fromString(string));
    }

    public UUID getId() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericId genericId = (GenericId) o;
        return Objects.equals(uuid, genericId.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
