DROP TABLE IF EXISTS Book;

CREATE TABLE Book (
  ISBN INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(100) NOT NULL,
  count INT DEFAULT 1
);

INSERT INTO Book (ISBN,name, description, count) VALUES
  (101,'All About Java', 'Written by John T. Includes deep understanding of java with examples.', 40),
  (102,'Spring 2.0', 'Written by David H. With greate expalination about how spring works.', 30),
  (103,'HTML&CSS', 'Written by Phillip Q. Learn to create and design web pages with html and CSS.', 40),
  (104,'Spring boot', 'Written by Holger M. With greate expalination about how spring boot works.', 30),
  (105,'Angular 2+', 'Written by Heiko G. All new angular concepts and examples.', 40),
  (106,'.Net', 'Written by Reduane H. Learn to build APIs with .Net.', 30),
  (107,'Head first JSP', 'Written by Alakija. All you know about HTML is here.', 50),
  (108,'C & C++', 'Written by Sameer. Explore with programming with c and c++.', 50);
  
CREATE TABLE BorrowList (
  id INT AUTO_INCREMENT,
  username VARCHAR(250) NOT NULL,
  ISBN int NOT NULL,
  booking_date date NOT NULL,
  return_date date NOT NULL,
  PRIMARY KEY (username, ISBN)
);

DROP TABLE IF EXISTS borrower;

CREATE TABLE borrower (
  card_id INT AUTO_INCREMENT PRIMARY KEY,
  username varchar(100) NOT NULL,
  phone varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
);

INSERT INTO borrower (username, phone,password) VALUES
  ('hexad', 490909090,'hexad');