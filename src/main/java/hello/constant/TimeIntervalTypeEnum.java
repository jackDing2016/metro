package hello.constant;


public enum TimeIntervalTypeEnum {

    // 15分钟间隔
    FIFTEEN_MINUTE( "1", "15min" ),

    // 5分钟间隔
    FIVE_MINUTE( "2", "5min" );

    private String code;

    private String name;

    TimeIntervalTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
