## Deployment Link
https://e-shop-rekkin-d55034d8dfdd.herokuapp.com/

# Refleksi 1 - Module 1
---
Pada exercise 1 ini, saya belajar cukup banyak mengenai clean coding dan struktur Spring Web.  
Saya mengusahakan menerapkan prinsip-prinsip clean code seperti penamaan variable yang jelas, 
penamaan function yang to the point, membuat satu function mengerjakan satu hal.  
Untuk secure coding, saya tidak mengimplementasikan secara sadar, basisnya hanya mengextend tutorialnya. 
Jika dari dependencies thymeleaf sudah menyediakan hal seperti input validation, sterialize input atau bisa saja
terdapat prinsip-prinsip yang saya tidak sadar ternyata telah diterapkan.  
Dan terakhir, saya rasa untuk fungsi mengenai delete/edit saya rasa belum cukup optimal. Selain itu, saya tidak menghandle jika returnnya null.  

# Refleksi 2 - Module 1
---
Ada rasa kepuasan melihat unit test saya hijau semua. Menurut saya, jumlah unit test yang perlu dilakukan itu tidak pasti.  
Membuat unit test bukan masalah kuantitas, melainkan kualitas test yang menguji kode yang telah dibuat. Begitu juga dengan coveragenya, 
coverage 100% tidak menandakan kode kita sempurna atau tidak bermasalah, bisa saja ada skenario yang belum dipikirkan untuk diujikan dan sebagainya.  
  
Jika setupnya mirip dan ada beberapa kode yang dapat diulang, bisa saja kita membuat sebuah class yang menyimpan variable-variable yang dibutuhkan 
untuk menghilangkan redundasi pada penulisan kode kita. Masalah-masalah yang dapat ditimbulkan seperti jika test kita sudah cukup banyak, 
kita makin sulit membaca kode yang semakin panjang dan kompleks dan artinya satu per satu. Walaupun saya sendiri, tidak menerapkan ini pada exercise 2.

# Refleksi 1 - Module 2
---
Untuk mengimprove code quality:
- Satu-satunya yang saya update dari module 1 adalah menambahkan null handling pada product controller
- Selain itu, code coverage sudah saya improve hingga 100%
- Saya juga merefactor filename untuk menyesuaikan dengan code controller
- Saya membereskan beberapa warning unused parameter/variable walau mungkin belum semua

Selain itu CI yang sudah saya implement meliputi :
- Gradlew Linter Test
- SonarQube Test
- OSSF Scorecard

Linter test menghandle unit test dan functional test, Sonar menghandle code quality, 
OSSF Scorecard menilai security walau saya ambil templatenya jadi banyak security belum aman, tetapi tetap lolos. 
Mungkin Config OSSF perlu diperketat dan juga saya rasa CI yang membantu proses PR juga dapat membantu.  

# Refleksi - Module 3
---
## Penerapan
- Penerapan DIP, saya menerapkan DIP dengan membuat sebuah interface untuk CarRepository, lalu pada CarService Kelas Car Repository saya referensikan ke Interface tersebut. 
- Penerapan SRP, saya menerapkan SRP dengan memisahkan Car Controller menjadi class tersendiri, dikarenakan prinsip ini bertujuan untuk membuat suatu kelas memilki satu tujuan. Jadi controller saya buat untuk mengatur dirinya sendiri dan menginisiasi id car langsung diclassnya.
- Penerapan ISP, saya menerapkan ISP dengan memecah semua fungsi di interface menjadi interface tersendiri (ex: Findable, Createable) yang nanti bisa dipasang-pasang.
- Penerapan OCP, saya menerapkan OCP dengan terdapat interface untuk general Service dan Repo yang mana penerapan findAll() nya mereturn data type yang berbeda. 
- Penerapan LSP, saya menerapkan LSP dengan membenarkan kesalahan penerapan prinsip ini sebelumnya Car Controller mengextends product controller padahal seharusnya tidak karena CarController tidak dapat menggantikan ProductController.

## Advantage
### Single Responbility Principle
Prinsip ini bertujuan supaya kita membuat suatu fungsi/method/class untuk mengerjakan satu hal saja. 
Dengan menerapkan prinsip ini, codebase kita lebih mudah untuk dimantain karena lebih mudah untuk mencari 
dan merefactor hal-hal di suatu saat dibutuhkan. 

### Open Closed Principle
Prinsip ini bertujuan untuk membatasi memodifikasi objek secara langsung, tetapi kita dapat memodifikasinya dengan extension. 
Prinsip ini memudahkan kita menambahkan fitur-fitur baru tanpa harus mengubah objek dasarnya dengan membuat subclass baru.

### Liskov Substitution Principle
Prinsip ini bertujuan menjaga konsistensi antar subclass dan superclassnya sehingga subclass dapat melakukan semua hal yang superclassnya bisa. 


### Interface Segregation Principle
Prinsip ini bertujuan untuk memecah interface-interface menjadi lebih spesifik sehingga suatu objek dapat mengimpementasi interface yang dibutuhkan. 
Prinsip ini memudahkan penggunaan interface untuk jadi modular dan agar tidak memaksakan fungsi/method yang tidak diperlukan

### Dependency Inversion Principle
Prinsip ini digunakan agar module-module itu depends ke sebuah abstraction bukan implementasinya

## Disadvantage
Kekurangan utama dalam tidak menerapkan prinsip SOLID yakni, codebase jadi sulit untuk dimaintain, baik karena kesulitan menemukan 
titik masalah maupun fungsi dapat saja hancur ketika menambahkan fungsi-fungsi baru. 

# Refleksi - Module 3
---
- Keunggulan yang saya rasa dengan menggunakan metode TDD adalah terkadang kita mempunyai kecenderungan membuat test yang memang 
sesuai dengan program kita. Dengan TDD, kita memastikan apa saja yang perlu dilakukan dulu (kriteria-kriteria model/fungsi dll), 
lalu kita buat programnya untuk memenuhi kebutuhan yang telah kita buat.  
  
Hasil analisa Unit Test saya terhadap  F.I.R.S.T. principle:
- Fast, saya rasa unit test saya dapat dijalankan dengan cukup cepat. Kira-kira tidak mencapai lebih dari 10 detik. 
- Independent, Saya membuat antar test tidak mengganggu test lain. Saya juga begunakan beforeEach untuk membuat state awal test sama. 
- Repeatable, Test saya dapat dijalankan baik di lokal maupun dengan CI saya di repo git
- Self-Validating, Test saya cukup jelas untuk diidentifikasi mana yang pass mana yang gagal dan letak kegagalannya
- Timely, Sesuai commit message saya.