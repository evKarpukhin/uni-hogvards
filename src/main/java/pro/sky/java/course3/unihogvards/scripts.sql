select * from student where age between 10 and 30;
select name from student;
select * from student where upper(name) like upper('%о%');
select * from student as st where st.age < st.id;
select * from student order by age;
