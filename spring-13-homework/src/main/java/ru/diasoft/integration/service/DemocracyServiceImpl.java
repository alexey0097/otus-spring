package ru.diasoft.integration.service;

import org.springframework.stereotype.Service;
import ru.diasoft.integration.domain.Oil;

import java.math.BigDecimal;

@Service
public class DemocracyServiceImpl implements DemocracyService {

    public final static String COUNTRY_RUSSIA = "russia";
    public final static String COUNTRY_AFGHANISTAN = "afghanistan";
    public final static String COUNTRY_IRAN = "iran";
    public final static String COUNTRY_USA = "usa";

    private final static String OIL_TYPE_PARAFFIN = "paraffin";
    private final static String OIL_TYPE_CYCLENE = "cyclene";
    private final static String OIL_TYPE_AROMATIC = "aromatic";

    @Override
    public Oil sendDemocracyToCountry(String country) {
        if (country.equalsIgnoreCase(COUNTRY_RUSSIA)) {
            return new Oil(COUNTRY_RUSSIA, OIL_TYPE_PARAFFIN, BigDecimal.valueOf(70.00));
        } else
        if (country.equalsIgnoreCase(COUNTRY_AFGHANISTAN)) {
            return new Oil(COUNTRY_AFGHANISTAN, OIL_TYPE_CYCLENE, BigDecimal.valueOf(46.00));
        } else
        if (country.equalsIgnoreCase(COUNTRY_IRAN)) {
            return new Oil(COUNTRY_IRAN, OIL_TYPE_AROMATIC, BigDecimal.valueOf(63.00));
        } else
            return new Oil(COUNTRY_USA, OIL_TYPE_AROMATIC, BigDecimal.valueOf(44.00));
    }

}
