--Window functions perform aggreagtion or operations on groups of rows
-- but they produce result for each row

--
select * from employees
emp_id , department salary


-- find the average saleary for each department
select department, avg(salary)
from employees
group by department order by department;

-- windeow performs aggregation over a window

OVER() clause constructs a window ,,when its empty it includes all the row

select emp_id,department,salary
avg(salary) over (partition by department) as department_average,
from employees;


select emp_id,department,salary
avg(salary) over (partition by department) as department_average,
min(salary) over (partition by department) as department_average,
max(salary) over (partition by department) as department_average,
from employees;
