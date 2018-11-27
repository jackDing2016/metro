package hello.constant;

public enum TransferTypeEnum {


     // 换进
    TRANSFER_IN( "1", "transferIn" ),
    // 换出
    TRANSFER_OUT( "2", "transferOut" );

    private String code;

    private String name;

    TransferTypeEnum(String code, String name) {
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
