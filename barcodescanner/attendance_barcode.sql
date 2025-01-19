CREATE DATABASE attendance_barcode;

USE attendance_barcode;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    namex VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    barcodenumber VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE attendance (
    attid INT AUTO_INCREMENT PRIMARY KEY,
    barcodenumber VARCHAR(50) NOT NULL,
    logdate DATE NOT NULL,
    time TIMESTAMP NOT NULL,
    FOREIGN KEY (barcodenumber) REFERENCES student(barcodenumber)
);
INSERT INTO student (namex, lastname, barcodenumber)
VALUES
    ('Saurabh', 'Wagh', '222111210233'),
    ('Shubham', 'Wankhede', '222111210234'),
    ('Pratik', 'chaudhary', '222111210017');

INSERT INTO student (namex, lastname, barcodenumber)
VALUES
    ('Rohit', 'Patil', '8906017290040');

						
select *  from attendance;

select * from student;

INSERT INTO student (namex, lastname, barcodenumber)
VALUES
    ('Kalpesh', 'Patil', '24051702241001'),
    ('Pranav', 'Patil', '24051702241002'),
    ('Nikhil', 'Matade', '24051702241003'),
    ('Vaibhav', 'Kumbhar', '24051702241004'),
    ('Srushti', 'Kharche', '24051702241005'),
    ('Chinmay', 'Nemade', '24051702241006'),
    ('Rohit', 'Damale', '24051702241007'),
    ('Dhanashri', 'Rane', '24051702241008'),
    ('Bhushan', 'Kumbhar', '24051702241009'),
    ('Bhagyashri', 'Bonde', '24051702241010'),
    ('Narendra', 'Mahajan', '24051702241011'),
    ('Jay', 'Pandit', '24051702241012'),
    ('Mahendra', 'Shimpi', '24051702241013'),
    ('Hitali', 'Chaudhari', '24051702241014'),
    ('Bharati', 'Patil', '24051702241015'),
    ('Priyanka', 'Patil', '24051702241016'),
    ('Kalyani', 'Mahajan', '24051702241017'),
    ('Kiran', 'Bodade', '24051702241018'),
    ('Krunal', 'Sonawane', '24051702241019'),
    ('Komal', 'Patil', '24051702241020'),
    ('Vivek', 'Sapkale', '24051702241021'),
    ('Divya', 'Aaware', '24051702241022'),
    ('Pankaj', 'Bhoi', '24051702241023'),
    ('Neha', 'Mahajan', '24051702241024'),
    ('Krushnal', 'Patil', '24051702241025'),
    ('Varsha', 'Patil', '24051702241026'),
    ('Pankaj', 'Wankhede', '24051702241027'),
    ('Nutan', 'Patil', '24051702241028'),
    ('Disha', 'Bhangale', '24051702241029'),
    ('Shubham', 'Wankhede', '24051702241030'),
    ('Saurabh', 'Wagh', '24051702241031'),
    ('Vaibhav', 'Dhake', '24051702241032'),
    ('Jayesh', 'Dhande', '24051702241033'),
    ('Parvati', 'Kharave', '24051702241034'),
    ('Yogesh', 'Prajapati', '24051702241035'),
    ('Kalyani', 'Mahajan', '24051702241036'),
    ('Jaydeep', 'Mahajan', '24051702241037'),
    ('Yogeshwari', 'Patil', '24051702241038'),
    ('Yash', 'Patil', '24051702241039'),
    ('Lokesh', 'Mahajan', '24051702241040'),
    ('Shubham', 'Sali', '24051702241041'),
    ('Hitesh', 'Mali', '24051702241042'),
    ('Pankaj', 'Mali', '24051702241043'),
    ('Manish', 'Borole', '24051702241044'),
    ('Aparna', 'Gaikwad', '24051702241045'),
    ('Ojashri', 'Chaudhari', '24051702241046'),
    ('Bhushan', 'Patil', '24051702241047'),
    ('Shubham', 'Rane', '24051702241048'),
    ('Saurav', 'Anjansonde', '24051702241049'),
    ('Nikita', 'Kirange', '24051702241050'),
    ('Nilesh', 'Koli', '24051702241051'),
    ('Savita', 'Bhoi', '24051702241052'),
    ('Chetana', 'Bhadane', '24051702241053'),
    ('Mayur', 'Mali', '24051702241054'),
    ('Atul', 'Koli', '24051702241055'),
    ('Damini', 'Jawale', '24051702241056'),
    ('Ganesh', 'Pol', '24051702241057'),
    ('Minal', 'Mahajan', '24051702241058'),
    ('Gaurav', 'Chaudhari', '24051702241059'),
    ('Dhanashri', 'Thakur', '24051702241060');


