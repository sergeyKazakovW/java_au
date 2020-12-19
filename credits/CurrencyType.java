enum CurrencyTypeEnumerated{
    DOLLARS,
    ROUBLES,
    YENS
};

public class CurrencyType {
    static CurrencyType DOLLARS;
    static CurrencyType ROUBLES;
    static CurrencyType YENS;
    static {
        DOLLARS = new CurrencyType();
        ROUBLES = new CurrencyType();
        YENS    = new CurrencyType();
    }
}
