package mx.com.ms.enums;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

public enum Status {
	
    ACTIVE(true),
    INACTIVE(false);

    private final Boolean flag;

    Status(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public static Status fromFlag(Boolean falg) {
        for (Status status : values()) {
            if (status.flag.equals(falg)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estatus inválido: " + falg);
    }
}
