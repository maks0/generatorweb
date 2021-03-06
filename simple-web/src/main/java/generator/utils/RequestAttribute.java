package generator.utils;

/**
 * @author maks
 */
public enum RequestAttribute {
    PAGE_CONTENT("pageContent"),
    PAGE_TYPE("pageType"),
    CAR_LIST("carList"),
    CAR("car"),
    DRIVER_LIST("driverList"),
    ERROR_MESSAGE("errorMessage"),
    SUCCESS_MESSAGE("successMessage"),
    PAGER("pager"),
    PAGER_LINK("pagerLink"),
    PAGE_TITLE("pageTitle"),
    REPORT("report"),
    REPORT_LIST("reportList"),
    REPORT_INFO("reportInfo");

    private String name;

    RequestAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
