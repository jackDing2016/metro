package hello.constant;

public enum TrafficTypeEnum {


    // 进站
    IMPORT( "1", "import" ),
    EXPORT( "2", "export" );

    private String code;

    private String name;

    TrafficTypeEnum(String code, String name) {
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
