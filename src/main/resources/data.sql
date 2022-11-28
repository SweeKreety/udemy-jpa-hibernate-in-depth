insert into course (id, name) values(1001,'Java');
insert into course (id, name) values(1002,'Hibernate');
insert into course (id, name) values(1003,'JPA');

insert into passport (id, number) values(4001,'E12345');
insert into passport (id, number) values(4002,'B12345');
insert into passport (id, number) values(4003,'C12345');

insert into student (id, name, passport_id) values(2001,'Swikriti', 4001);
insert into student (id, name, passport_id) values(2002,'Ram', 4002);
insert into student (id, name, passport_id) values(2003,'Max', 4003);

insert into review (id, rating, description, course_id) values(5001,'3','Decent Course', 1001);
insert into review (id, rating, description, course_id) values(5002,'4','Very Good Course', 1003);
insert into review (id, rating, description, course_id) values(5003,'5','Great Course', 1002);

insert into student_course(student_id, course_id) values (2001,1001);
insert into student_course(student_id, course_id) values (2003,1001);
insert into student_course(student_id, course_id) values (2002,1002);
insert into student_course(student_id, course_id) values (2001,1003);

