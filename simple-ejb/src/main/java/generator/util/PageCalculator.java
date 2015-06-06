package generator.util;


/**
 * @author maks
 */

public class PageCalculator {

    private static final Integer FIRST_PAGE = 1;

    private static PageCalculator CALCULATOR = null;
    
    private PageCalculator () {};
    
    public static PageCalculator getInstance(){
        if (CALCULATOR == null) {
            CALCULATOR = new PageCalculator();
        }
        return CALCULATOR;
    }


//    public <T> Pager createPager (Class <T> entity, Integer pageNumber, Integer pageSize) {
//
//        GenericDAO <T> dao = null;
//        Pager pager = null;
//        try {
//            dao = new GenericDAO<T>(entity);
//            pager = calculatePages(pageNumber, pageSize, dao.count());
//        } finally {
//            if (dao != null) {
//                dao.close();
//            }
//        }
//        return pager;
//    }

    public Pager calculatePages(Integer pageNumber, Integer pageSize, Integer amount) {
        if (pageNumber == null) {
            throw new IllegalArgumentException("Integer 'pageNumber' must not be a null.");
        }
        if (pageSize == null) {
            throw new IllegalArgumentException("Integer 'pageSize' must not be a null.");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Integer 'amount' must not be a null.");
        }

        Pager pager = new Pager();
        pager.setFirstPage(calculateFirstPage(pageNumber));
        pager.setPreviousPage(calculatePreviousPage(pageNumber));
        pager.setNextPage(calculateNextPage(pageNumber, pageSize, amount));
        pager.setLastPage(calculateLastPage(pageNumber, pageSize, amount));
        return pager;
    }

    private Integer calculateLastPage(Integer pageNumber, Integer pageSize, Integer amount) {
        int maxPage = calculateLastPageNumber(pageSize, amount);
        if (pageNumber >= maxPage) {
            return null;
        }
        return maxPage;
    }

    private Integer calculateNextPage(Integer pageNumber, Integer pageSize, Integer amount) {
        int maxPage = calculateLastPageNumber(pageSize, amount);
        if(pageNumber >= maxPage) {
            return null;
        }
        return ++pageNumber;
    }

    private Integer calculatePreviousPage(Integer pageNumber) {
        if (pageNumber <= FIRST_PAGE) {
            return null;
        }
        return --pageNumber;
    }

    private Integer calculateFirstPage(Integer pageNumber) {
        if (pageNumber <= FIRST_PAGE) {
            return null;
        } else {
            return FIRST_PAGE;
        }
    }

    private int calculateLastPageNumber(int pageSize, int amount) {
        return (int) Math.ceil((float) amount / pageSize);
    }

}
