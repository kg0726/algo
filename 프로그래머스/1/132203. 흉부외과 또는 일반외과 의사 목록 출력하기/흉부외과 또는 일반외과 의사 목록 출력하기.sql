-- 코드를 입력하세요 흉부외과 or 일반외과, 고용일자 내림차순, 이름 오름차순
SELECT dr_name, dr_id, mcdp_cd, hire_ymd
from doctor
where mcdp_cd in ('CS', 'GS')
order by hire_ymd desc, dr_name;