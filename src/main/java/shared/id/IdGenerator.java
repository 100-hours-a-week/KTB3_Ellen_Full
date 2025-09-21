package shared.id;

import domain.enums.EntityType;

public interface IdGenerator {

    int next(EntityType entityType);
    int peek(EntityType entityType);
}
