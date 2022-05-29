package com.summer.dev.standbot.constant.keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 29.05.2022
 * Time: 17:57
 */
public final class EquipmentSelectCommand implements Commandable {
    public static final String EQUIPMENT_SELECT_PREFIX = "EQUIPMENT_SELECT_PREFIX_";
    // FIXME dirty hack for split working
    public static final String STAND = "STAND_NAME";
    public static final String METRIC = "METRIC_NAME";
    public static final String INDEPENDENT_SESSION = "INDEPENDENT_SESSION";
    public static final String DEPENDENT_SESSION = "DEPENDENT_SESSION";
}
