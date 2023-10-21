package com.example.demo;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        TuDien dictionary = new TuDien();

        TongHop nghia1 = new TongHop("Danh từ","Phat am 1" , "Y nghĩa danh từ 1", "Câu ví dụ tiếng Việt danh từ 1", "Câu ví dụ tiếng Anh danh từ 1");
        TongHop nghia2 = new TongHop("Động từ", "Phat am 2" ,"Y nghĩa động từ 1", "Câu ví dụ tiếng Việt động từ 1", "Câu ví dụ tiếng Anh động từ 1");
        TongHop nghia3 = new TongHop("Danh từ","Phat am 1" ,"Y nghĩa danh từ 2", "Câu ví dụ tiếng Việt danh từ 2", "Câu ví dụ tiếng Anh danh từ 2");

        dictionary.themTu("example", nghia1);
        dictionary.themTu("example", nghia2); // Thêm nghĩa thứ hai cho từ "example"
        dictionary.themTu("another", nghia3);

        List<TongHop> tuTra = dictionary.traTu("example") ;
        if (tuTra != null) {
            for (TongHop nghia : tuTra) {
                nghia.print();
            }
        } else {
            System.out.println("Từ không tồn tại trong từ điển.");
        }
    }
}

