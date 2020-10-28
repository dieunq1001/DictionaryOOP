# DictionaryOOP
Từ điển bài tập lớn 1 môn OOP

## Những chức năng của từ điển: 
- Có mục tra cứu Anh-Việt và Việt-Anh
- Có gợi ý từ khi tra cứu từ
- Tính năng thêm, xóa, sửa từ (chưa hoàn thiện 100%)
- Tra cứu bằng Google API
- Có TextToSpeech


## Các bước cài đặt từ điển của nhóm em:
- Clone về từ github
- Vào trong code -> hàm main -> thay đổi đường dẫn file E_V và V_E và fxml ( 2 file dữ liệu có sẵn trong package comps)
- Giải nén thư mục freetts (tìm thấy trong thư mục comps) -> thêm vào thư viện của project từ điển
![Annotation 2020-10-28 142458](https://user-images.githubusercontent.com/63422116/97404917-6d1e7c00-1929-11eb-8db1-561e30fff9c5.png)
- Chạy chương trình -  lưu ý cần có kết nối mạng để sử dụng API của Google.

## Hướng dẫn sử dụng các chức năng của từ điển:
- 2 tab E-V và V-E có 3 nút với các chức năng tìm kiếm từ file dữ liệu bên trên ( tương ứng với 2 nút search và tìm kiếm ở mỗi tab), nút sử dụng google API để dịch các từ không có sẵn trong từ điển hoặc câu ( tương ứng với 2 nút translate và dịch - chức năng cần sử dụng mạng ) và nút phát âm từ - câu ( ứng với 2 nút voice và phát âm)
![search2](https://user-images.githubusercontent.com/63422116/97404983-8e7f6800-1929-11eb-9eee-51ee1da1ce82.png)
- Tab History dùng để xem lại những từ đã được tìm kiếm, có thể bấm vào từ đó để xem lại nghĩa.
![history](https://user-images.githubusercontent.com/63422116/97405471-490f6a80-192a-11eb-95a8-c01fe284dcef.PNG)
- Tab Add dùng để thêm từ, có 2 textfield có ghi chú sẵn. Khi thêm từ có trong bộ nhớ từ điển thì sẽ có thông báo lỗi, không thì thông báo thành công. Sau khi thêm xong nếu thoát chương trình thì lần sau vào lại sẽ vẫn còn từ đó.
![add](https://user-images.githubusercontent.com/63422116/97405022-9fc87480-1929-11eb-91c3-b24b3d04926a.PNG)
- Tab Remove and Change dùng để xóa từ hoặc sửa nghĩa. Người dùng cần nhập từ cần thay đổi vào ô textfield bé đầu tiên, sau đó bấm vào nút Anh-Việt hoăc Việt-Anh tương ứng. Nếu từ đó có trong từ điển thì sẽ hiển thị nghĩa kèm html, nếu không thì sẽ có thông báo từ này chưa có trong từ điển. 2 nút xóa để xóa từ đó khỏi từ điển tương ứng đã được kiểm tra ở bên trên. 2 nút thay đổi nghĩa cũng tương tự.
![removeandchange](https://user-images.githubusercontent.com/63422116/97405075-b2db4480-1929-11eb-9460-c8eb084a0208.PNG)
- Tab About Us là để xem thông tin người tạo ra từ điển ( nhóm em).
![aboutus](https://user-images.githubusercontent.com/63422116/97405106-ba9ae900-1929-11eb-9adf-977961354100.PNG)
