package booksearch.service;

import booksearch.dao.BookDAO;
import booksearch.vo.BookVO;
import javafx.collections.ObservableList;

public class BookSearchService {

    public ObservableList<BookVO> searchBookByKeyword(String keyword) {
        // 이 안에서는 로직 처리를 한다.
        // 만약 Database 처리를 하게 되면 DAO 만들어서 사용해야 한다.
        // 별 다른 로직처리를 할 게 없다면 그냥 DB 처리만 하면 된다.
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
