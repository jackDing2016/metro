package hello.constant;

public enum PressureTypeEnum {

    PLATEFORM( 1, "站台" ),

    Escalator( 2,  "楼扶梯"),

    GATE( 3,  "闸机"),

    ENTRANCE( 4, "出入口" ),

    TRANSFER_PASSAGE( 5, "换乘通道" );

    private Integer code;

    private String name;

    PressureTypeEnum(Integer code, String name) {
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
}
