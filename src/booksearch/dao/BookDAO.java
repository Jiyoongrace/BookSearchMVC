package booksearch.dao;

import booksearch.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {
    private Connection con;
    public BookDAO() {
        // JDBC Driver Loading
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String id = "root";
            String pw = "jiyun9163!";
            String JDBC_URL = "jdbc:mysql://localhost:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
            con = DriverManager.getConnection(JDBC_URL, id, pw);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<BookVO> select(String keyword) {
        // IN Parameter를 이용해서 PreparedStatement를 생성한다.
        String sql = "SELECT bisbn, btitle, bprice, bauthor FROM book " + "WHERE btitle Like ?";
        ObservableList<BookVO> data = FXCollections.observableArrayList();
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            // 당연히 In Parameter를 채워줘야 실행이 가능하다.
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();


            // rs는 결과 레코드 집합의 포인터이다. 이것을 움직여서 우리가 select한 결과를 얻는다.
            while (rs.next()) { // 계속해서 내려간다.
                BookVO book = new BookVO(
                        rs.getString("bisbn"),
                        rs.getString("btitle"),
                        rs.getInt("bprice"),
                        rs.getString("bauthor"));
                data.add(book);
            }

        } catch (Exception e) {

        }
        return data;
    }

    public ObservableList<BookVO> delete(String keyword) {
        String sqlDelete = "DELETE FROM book WHERE bisbn = ?";
        String sqlSelect = "SELECT bisbn, btitle, bprice, bauthor FROM book";
        ObservableList<BookVO> data = FXCollections.observableArrayList();

        try {
            // 먼저 책을 삭제한다.
            PreparedStatement pstmtDelete = con.prepareStatement(sqlDelete);
            pstmtDelete.setString(1, keyword);
            int rowsAffected = pstmtDelete.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(keyword + "을(를) 삭제했습니다.");

                // 삭제 후에 변경된 데이터를 다시 조회한다.
                PreparedStatement pstmtSelect = con.prepareStatement(sqlSelect);
                ResultSet rs = pstmtSelect.executeQuery();

                // 새로 조회한 결과를 ObservableList에 추가한다.
                while (rs.next()) {
                    BookVO book = new BookVO(
                            rs.getString("bisbn"),
                            rs.getString("btitle"),
                            rs.getInt("bprice"),
                            rs.getString("bauthor"));
                    data.add(book);
                }
            } else {
                System.out.println("삭제할 데이터가 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }


}
