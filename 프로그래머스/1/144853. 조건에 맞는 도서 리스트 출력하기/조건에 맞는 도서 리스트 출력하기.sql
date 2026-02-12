select book_id, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') as d
from book b
where year(b.PUBLISHED_DATE) = 2021 and category = '인문'
order by d;