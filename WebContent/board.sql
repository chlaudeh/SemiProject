drop table board;
create table board
(
     num number(5) primary key, -- 글번호
     writer varchar2(10),--작성자
     title varchar2(10), --제목
     content varchar2(50), -- 내용
     hit number(8), -- 조회수
     pwd varchar2(10) -- 비밀번호(삭제시 필요)
);