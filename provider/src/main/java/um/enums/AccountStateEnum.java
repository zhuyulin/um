package um.enums;

/**
 * Created by Yuleen on 2016/12/31.
 */
public enum AccountStateEnum {

    NORMAL("1"),DELETE("0"),FROZEN("2");


    private String state;
    private AccountStateEnum(String station) {
        this.state = station;
    }
    public String getState(){
        return state;
    }


}
