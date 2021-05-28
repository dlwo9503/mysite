-- scheme
desc user;

-- join(insert)
insert into user values(null, '관리자', 'admin@mysite.com', '1234', 'male');

-- user list(select)
select * from user;

-- login(select)
select no, name from user where email='dlwo9503@naver.com' and password='123';