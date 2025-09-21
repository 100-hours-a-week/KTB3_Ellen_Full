package domain.enums;

public enum EmployeeType {

    MANAGER(1), ENGINEER(2), INTERN(3);

    private final int code;

    EmployeeType(int code) {
        this.code = code;
    }

    public static EmployeeType fromCode(int code) {
        for (EmployeeType type : values()) {
            if (type.code == code) return type;
        }
        throw new IllegalArgumentException("유효하지 않은 코드입니다: " + code);
    }
}
