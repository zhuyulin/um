package um.emum;

/**
 * Created by Yuleen on 2016/12/31.
 */
public enum AccountStationEmum {

    NORMAL("1"),DELETE("0"),FROZEN("2");

    private String station;
    private AccountStationEmum(String station) {
        this.station = station;
    }
    public String getStation(){
        return station;
    }


}
