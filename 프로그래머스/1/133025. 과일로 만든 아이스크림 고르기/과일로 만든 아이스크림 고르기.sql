-- 코드를 입력하세요

SELECT f.flavor
from FIRST_HALF f join ICECREAM_INFO i
where f.TOTAL_ORDER >= 3000 and i.flavor = f.flavor and i.INGREDIENT_TYPE = 'fruit_based'
order by f.total_order desc;