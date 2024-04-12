package booksearch.controller;

import booksearch.service.BookSearchService;
import booksearch.vo.BookVO;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchController implements Initializable {

    @FXML private TextField textField;
    @FXML private Button searchBtn;
    @FXML private Button deleteBtn;
    @FXML private TableView tableView;

    @FXML private TableColumn<BookVO, String> isbn;
    @FXML private TableColumn<BookVO, String> title;
    @FXML private TableColumn<BookVO, Integer> price;
    @FXML private TableColumn<BookVO, String> author;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        isbn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        price.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        author.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        searchBtn.setOnAction(e -> {
            // 검색 버튼을 눌렀을 때 해야 하는 작업
            // 실제 로직 처리는 Controller가 하지 않는다.
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
            BookVO selectedBook = (BookVO) tableView.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                System.out.println("삭제할 책을 선택해주세요.");
                return;
            }

            String keywordDelete = selectedBook.getBisbn();

            String currentKeyword = textField.getText();

            BookSearchService service = new BookSearchService();
            ObservableList<BookVO> updatedData = service.deleteBookByKeyword(keywordDelete);
            ObservableList<BookVO> searchData = service.searchBookByKeyword(currentKeyword);

            tableView.setItems(searchData);

            tableView.getSelectionModel().clearSelection();
        });


    }
}
