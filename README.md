# DictionaryOOP
từ điển bài tập lớn 1 môn OOP
Những chức năng của từ điển: 
- Có mục tra cứu Anh-Việt và Việt-Anh
- Có gợi ý từ khi tra cứu từ
- Tính năng thêm, xóa, sửa từ (chưa hoàn thiện 100%)
- Tra cứu bằng Google API
- Có TextToSpeech


Các bước cài đặt từ điển của nhóm em:
- Clone về từ github
- Vào trong code -> hàm main -> thay đổi đường dẫn file E_V và V_E và fxml ( 2 file dữ liệu có sẵn trong package comps)
- Giải nén thư mục freetts (tìm thấy trong thư mục comps) -> thêm vào thư viện của project từ điển
- Chạy chương trình -  lưu ý cần có kết nối mạng để sử dụng API của Google.

Hướng dẫn sử dụng các nút bấm của từ điển:
- 2 tab E-V và V-E có 3 nút với các chức năng tìm kiếm từ file dữ liệu bên trên ( tương ứng với 2 nút search và tìm kiếm ở mỗi tab), nút sử dụng google API để dịch các từ không có sẵn trong từ điển hoặc câu ( tương ứng với 2 nút translate và dịch - chức năng cần sử dụng mạng ) và nút phát âm từ - câu ( ứng với 2 nút voice và phát âm)
- Tab Add dùng để thêm từ, có 2 textfield có ghi chú sẵn. Khi thêm từ có trong bộ nhớ từ điển thì sẽ có thông báo lỗi, không thì thông báo thành công. Sau khi thêm xong nếu thoát chương trình thì lần sau vào lại sẽ vẫn còn từ đó.
- Tab Remove and Change dùng để xóa từ hoặc sửa nghĩa. Người dùng cần nhập từ cần thay đổi vào ô textfield bé đầu tiên, sau đó bấm vào nút Anh-Việt hoăc Việt-Anh tương ứng. Nếu từ đó có trong từ điển thì sẽ hiển thị nghĩa kèm html, nếu không thì sẽ có thông báo từ này chưa có trong từ điển. 2 nút xóa để xóa từ đó khỏi từ điển tương ứng đã được kiểm tra ở bên trên. 2 nút thay đổi nghĩa cũng tương tự.
- Tab About Us là để xem thông tin người tạo ra từ điển ( nhóm em).
