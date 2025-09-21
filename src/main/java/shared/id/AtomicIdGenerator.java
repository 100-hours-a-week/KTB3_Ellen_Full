package shared.id;

import domain.enums.EntityType;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicIdGenerator implements IdGenerator {

    private final EnumMap<EntityType, AtomicInteger> atomicIntegerEnumMap;

    public AtomicIdGenerator(EnumMap<EntityType, AtomicInteger> atomicIntegerEnumMap) {
        this.atomicIntegerEnumMap = atomicIntegerEnumMap;
        for (EntityType t : EntityType.values()) {
            atomicIntegerEnumMap.put(t, new AtomicInteger(1)); // 기본 시작값 1
        }
    }

    @Override
    public int next(EntityType entityType) {
        return atomicIntegerEnumMap.get(entityType).getAndIncrement();
    }

    @Override
    public int peek(EntityType entityType) {
        return atomicIntegerEnumMap.get(entityType).get();
    }
}
