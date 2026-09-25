-- 코드를 입력하세요
SELECT f.flavor
from first_half f join (select sum(j.total_order) as j_total, j.flavor from july j group by j.flavor) j2
on f.flavor = j2.flavor
order by f.total_order + j2.j_total desc
limit 3;