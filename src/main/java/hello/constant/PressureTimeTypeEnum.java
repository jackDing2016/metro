package hello.constant;

public enum PressureTimeTypeEnum {

    WEEKDAY( 1, "平日" ),

    HOLIDAY( 2,  "节假日");

    private Integer code;

    private String name;

    PressureTimeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PressureTimeTypeEnum getByCode(Integer code ) {
        for( PressureTimeTypeEnum e : values()) {
            if( e.code.equals( code ) ) return e;
        }
        return null;
    }
}
