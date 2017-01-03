package um.emum;

/**
 * Created by Yuleen on 2016/12/31.
 */
public enum AccountStateEmum {

    NORMAL("1"),DELETE("0"),FROZEN("2");


    private String state;
    private AccountStateEmum(String station) {
        this.state = station;
    }
    public String getState(){
        return state;
    }


}
