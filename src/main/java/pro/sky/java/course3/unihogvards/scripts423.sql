SELECT st.name, st.age, fc.name FROM student as st INNER JOIN faculty as fc ON st.faculty_id = fc.id;

SELECT st.name, st.age, fc.name, fc.color FROM student as st
INNER JOIN faculty as fc ON st.faculty_id = fc.id
INNER JOIN avatar as av ON av.student_id = st.id