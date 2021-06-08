desc board;

select max(group_no)+1 from board;

insert into board values (null, 'a', 'a', now(), 0, 0, 0, 0, 1);

select a.no, a.title, a.depth, a.hit, b.no as userNo, b.name, a.group_no from board a, user b where a.user_no = b.no order by a.group_no DESC, a.order_no ASC;
insert into board values(null, 'ㅁㄴㅇㄴㅇ', 'ㅁㄴㅇ', now(), 0,(select max(group_no)+1 from board ALIAS_FOR_SUBQUERY), 0, 0, 1);
insert into board values(null, 'ㅁㄴㅇㄴㅇ', 'ㅁㄴㅇ', now(), 0, 0, 0, 0, 1);
insert into board values(null, 'aaa', 'aaa', now(), 0, 1, 1, 1, 2);

select * from board;	
update board set order_no = order_no + 1 where group_no = 0 and order_no >= 1;

delete from board where no = 12;

select no, title, contents, depth from board where no = 38;