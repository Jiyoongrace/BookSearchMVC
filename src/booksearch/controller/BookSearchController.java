package booksearch.controller;

import booksearch.service.BookSearchService;
import booksearch.vo.BookVO;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchController implements Initializable {

    @FXML private TextField textField;
    @FXML private Button searchBtn;
    @FXML private Button deleteBtn;
    @FXML public TableView tableView;

    @FXML private TableColumn<BookVO, String> isbn;
    @FXML private TableColumn<BookVO, String> title;
    @FXML private TableColumn<BookVO, Integer> price;
    @FXML private TableColumn<BookVO, String> author;

    private String deleteIsbn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // 화면 초기화

        // 이벤트 처리
        // VO의 어떤 field가 tableView의 어떤 column에 매핑되는지를 설정
        isbn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        price.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        author.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        tableView.setRowFactory(e -> {
            TableRow<BookVO> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 1 && !row.isEmpty()) {
                    BookVO book = row.getItem();
                    deleteIsbn = book.getBisbn();
                }
            });
            return row;
        });

        searchBtn.setOnAction(e -> {
            // 검색 버튼을 눌렀을 때 해야 하는 작업
            // 실제 로직 처리는 Controller가 하지 않는다.
            // 로직 처리를 하는 객체를 이용해서 일을 시킨다. (method를 호출)
            // 처리된 결과를 받아서 화면 제어를 한다.
            // 로직 객체를 따로 만들어서 사용해야 한다.
            // 서비스 객체가 필요하다.
            BookSearchService service = new BookSearchService();

            // 해당 객체를 통해 비즈니스 수행
            String keyword = textField.getText();
            ObservableList<BookVO> data = service.searchBookByKeyword(keyword);
            tableView.setItems(data);
        });

        deleteBtn.setOnAction(e -> {
            // 삭제 버튼을 눌렀을 때 해야 하는 작업
            // 삭제할 책의 ISBN을 가져온다.
            BookSearchService service = new BookSearchService();
            ObservableList<BookVO> list = service.deleteBookByKeyword(deleteIsbn);
            tableView.setItems(list);
        });


    }
}
