package booksearch.service;

import booksearch.dao.BookDAO;
import booksearch.vo.BookVO;
import javafx.collections.ObservableList;

public class BookSearchService {

    // transaction
    public ObservableList<BookVO> searchBookByKeyword(String keyword) {
        // 이 안에서는 로직 처리를 한다.
        // 만약 Database 처리를 하게 되면 DAO 만들어서 사용해야 한다.
        // 별 다른 로직처리를 할 게 없다면 그냥 DB 처리만 하면 된다.
        // 일반적인 로직 처리 코드
        // Database 처리도 당연히 필요하지만 Database 처리는 Service에서 직접 하지 않는다.
        // DAO한테 위임해서 처리하고 결과만 받아온다.
        BookDAO dao = new BookDAO();
        ObservableList<BookVO> result =  dao.select(keyword);

        return result;
    }
    // 로직 처리 객체
    // 당연히 이 안에는 business method가 존재한다.
    public ObservableList<BookVO> deleteBookByKeyword(String keyword) {
        // 이 안에서는 로직 처리를 한다.
        // 만약 Database 처리를 하게 되면 DAO 만들어서 사용해야 한다.
        // 별 다른 로직처리를 할 게 없다면 그냥 DB 처리만 하면 된다.
        BookDAO dao = new BookDAO();
        ObservableList<BookVO> result =  dao.delete(keyword);

        return result;
    }
}
