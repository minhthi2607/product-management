# 📦 CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM (JAVA CONSOLE)

## 1. Giới thiệu
Đây là chương trình **Quản lý sản phẩm chạy trên Java Console**, được xây dựng trong quá trình học **Module 2 – Java Core**.  
Mục tiêu của chương trình là áp dụng các kiến thức Java đã học để xây dựng một ứng dụng hoàn chỉnh, có tổ chức code rõ ràng, xử lý dữ liệu, validate đầu vào và lưu trữ bằng file.

---

## 2. Mục tiêu
- Áp dụng lập trình hướng đối tượng (OOP)
- Rèn luyện tư duy tổ chức code theo mô hình rõ ràng
- Sử dụng Collection, Exception, Regex và File I/O
- Sử dụng Comparator để sắp xếp dữ liệu linh hoạt
- Xây dựng chương trình console chạy ổn định, không crash khi nhập sai dữ liệu

---

## 3. Chức năng chính
Chương trình cung cấp các chức năng sau:

1. Thêm sản phẩm
2. Sửa thông tin sản phẩm theo ID
3. Xóa sản phẩm theo ID
4. Hiển thị danh sách sản phẩm
5. Tìm kiếm sản phẩm theo tên
6. Sắp xếp sản phẩm theo giá tăng dần
7. Sắp xếp sản phẩm theo giá giảm dần
8. Xuất danh sách sản phẩm ra file CSV
0. Thoát chương trình

---

## 4. Mô tả các thành phần

### 4.1 Model
- `Product`: đại diện cho sản phẩm, gồm các thuộc tính:
    - id
    - name
    - price
    - quantity
    - category
- Class `Product` implements `Serializable` để hỗ trợ ghi/đọc file.

---

### 4.2 Service
- `ProductService`:
    - Quản lý danh sách sản phẩm bằng `List<Product>`
    - Xử lý logic nghiệp vụ (thêm, sửa, xóa, tìm kiếm, sắp xếp)
    - Validate dữ liệu trước khi xử lý
    - Ghi dữ liệu ra file sau mỗi thay đổi

---

### 4.3 Utils
- `FileUtils`:
    - Ghi và đọc danh sách sản phẩm từ file `products.dat` bằng `ObjectOutputStream` và `ObjectInputStream`
    - Hỗ trợ xuất dữ liệu sản phẩm ra file CSV
- `ValidateUtils`:
    - Kiểm tra dữ liệu đầu vào bằng Regular Expression
    - Ví dụ: validate tên sản phẩm (cho phép tiếng Việt, số, dấu cách)

---

### 4.4 Exception
Chương trình sử dụng **Custom Exception** để xử lý lỗi:
- `DuplicateProductIdException`: ID sản phẩm bị trùng
- `InvalidPriceException`: Giá sản phẩm không hợp lệ
- `ProductNotFoundException`: Không tìm thấy sản phẩm theo ID

---

### 4.5 Comparator
- Chương trình sử dụng **Comparator** để sắp xếp danh sách sản phẩm theo giá:
    - Sắp xếp giá tăng dần
    - Sắp xếp giá giảm dần
- Việc sử dụng Comparator giúp:
    - Tách biệt logic sắp xếp khỏi class `Product`
    - Dễ mở rộng thêm các kiểu sắp xếp khác trong tương lai

---

## 5. Xuất dữ liệu ra file CSV
- Chương trình hỗ trợ **xuất danh sách sản phẩm ra file CSV** (`products.csv`)
- File CSV có thể:
    - Mở bằng Excel, Google Sheets
    - Dùng để tích hợp với các hệ thống khác
- Dữ liệu trong file CSV bao gồm:
    - ID
    - Tên sản phẩm
    - Giá
    - Số lượng
    - Danh mục

---

## 6. Luồng xử lý chính
1. Người dùng chọn chức năng từ menu
2. Nhập dữ liệu từ console
3. Service validate dữ liệu
4. Thực hiện xử lý nghiệp vụ
5. Ghi dữ liệu xuống file (DAT hoặc CSV) nếu hợp lệ
6. Hiển thị kết quả hoặc thông báo lỗi phù hợp

---

## 7. Công nghệ & kiến thức sử dụng
- Java Core
- OOP (Encapsulation, Separation of Concerns)
- Collection Framework (`ArrayList`)
- Comparator
- Custom Exception
- Regular Expression (Regex)
- File I/O (Object Stream, CSV)
- Java Console

---

## 8. Hướng dẫn chạy chương trình
1. Mở project bằng IDE (IntelliJ IDEA / Eclipse)
2. Chạy file `Main.java`
3. Thao tác theo menu hiển thị trên console

---

## 9. Kết luận
Chương trình giúp củng cố kiến thức Java Core và rèn luyện kỹ năng xây dựng một ứng dụng console hoàn chỉnh, có tổ chức code rõ ràng, xử lý lỗi tốt, hỗ trợ sắp xếp linh hoạt và đảm bảo dữ liệu an toàn thông qua việc lưu trữ file và xuất CSV.
